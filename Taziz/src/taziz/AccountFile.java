/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taziz;

/**
 *
 * @author rimas
 */

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Fixed-length account records stored in accounts.dat.
 * Record layout: ID(20), password(30), role(1), name(50), university ID(20).
 */
public final class AccountFile {
    private static final String FILE_NAME = "accounts.dat";
    private static final int ID_LEN = 20;
    private static final int PASSWORD_LEN = 30;
    private static final int ROLE_LEN = 1;
    private static final int NAME_LEN = 50;
    private static final int UNIVERSITY_ID_LEN = 20;
    private static final long RECORD_SIZE =
            2L * (ID_LEN + PASSWORD_LEN + ROLE_LEN + NAME_LEN + UNIVERSITY_ID_LEN);

    private AccountFile() {}

    private static RandomAccessFile open() throws IOException {
        RandomAccessFile file = new RandomAccessFile(new File(FILE_NAME), "rw");
        if (file.length() == 0) seedSampleAccounts(file);
        return file;
    }

    private static void seedSampleAccounts(RandomAccessFile file) throws IOException {
        writeAccount(file, new Account("admin", "admin123", 'A', "System Admin", "ADMIN"));
        writeAccount(file, new Account("2308188", "1234", 'S', "Rimas Mahmoud", "2308188"));
        writeAccount(file, new Account("2314721", "1234", 'S', "Rana AlMisbahi", "2314720"));
        writeAccount(file, new Account("2305845", "1234", 'S', "Sadeem AlJehani", "2305845"));
        writeAccount(file, new Account("2307945", "1234", 'S', "Moudi Algarawi", "2307945"));
    }

    private static void writeAccount(RandomAccessFile file, Account account) throws IOException {
        FixedRecordIO.writeFixedString(file, account.getId(), ID_LEN);
        FixedRecordIO.writeFixedString(file, account.getPassword(), PASSWORD_LEN);
        FixedRecordIO.writeFixedString(file, String.valueOf(account.getRole()), ROLE_LEN);
        FixedRecordIO.writeFixedString(file, account.getName(), NAME_LEN);
        FixedRecordIO.writeFixedString(file, account.getUniversityId(), UNIVERSITY_ID_LEN);
    }

    private static Account readAccount(RandomAccessFile file) throws IOException {
        String id = FixedRecordIO.readFixedString(file, ID_LEN);
        String password = FixedRecordIO.readFixedString(file, PASSWORD_LEN);
        String role = FixedRecordIO.readFixedString(file, ROLE_LEN);
        String name = FixedRecordIO.readFixedString(file, NAME_LEN);
        String universityId = FixedRecordIO.readFixedString(file, UNIVERSITY_ID_LEN);
        if (role.isEmpty()) throw new IOException("Account record has an empty role.");
        return new Account(id, password, role.charAt(0), name, universityId);
    }

    public static synchronized Account authenticate(String id, String password) throws IOException {
        try (RandomAccessFile file = open()) {
            for (long offset = 0; offset + RECORD_SIZE <= file.length(); offset += RECORD_SIZE) {
                file.seek(offset);
                Account account = readAccount(file);
                if (account.getId().equals(id) && account.getPassword().equals(password)) {
                    return account;
                }
            }
        }
        return null;
    }

    /** Adds an account record. Intended for preloading demo accounts, not a public registration screen. */
    public static synchronized void addAccount(Account account) throws IOException {
        try (RandomAccessFile file = open()) {
            for (long offset = 0; offset + RECORD_SIZE <= file.length(); offset += RECORD_SIZE) {
                file.seek(offset);
                Account existing = readAccount(file);
                if (existing.getId().equals(account.getId())) {
                    throw new IllegalArgumentException("An account with this ID already exists.");
                }
            }
            file.seek(file.length());
            writeAccount(file, account);
        }
    }
}
