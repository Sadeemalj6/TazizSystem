/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taziz;

/**
 *
 * @author rimas
 */

/** Holds the account authenticated in the current application session. */
public final class CurrentUser {
    private static Account account;

    private CurrentUser() {}

    public static void setAccount(Account currentAccount) {
        account = currentAccount;
    }

    public static Account getAccount() {
        return account;
    }

    public static String getName() {
        return account == null ? "Unknown User" : account.getName();
    }

    public static void clear() {
        account = null;
    }
}
