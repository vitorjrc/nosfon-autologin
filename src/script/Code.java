/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package script;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Sergio
 */
public class Code {

    /*
    Returns true if internet connection is stablished and false if not
     */
    private boolean netIsAvailable() {

        try {
            // connects to a known website to check if internet connection is stablished and working
            final URL url = new URL("https://coinmarketcap.com/");
            final URLConnection conn = url.openConnection();
            conn.connect();
            return true;

        } catch (MalformedURLException e) {
            System.err.println("Exceção...");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /*
    Launches a new instance of the predefined browser (Brave, in our case) and
    close it after (supposedly) enough time for the login prompted by nosfonwifi
    to be fulfilled by browser's cookies
     */
    private void execBrowser() {

        try {
            // entering random website will lead us to the login page of nosfon
            Process p = Runtime.getRuntime().exec(new String[]{"explorer", "http://www.sapo.pt"});
            // wait 30 seconds and close the browser after that
            System.out.println("Sleeping 30 sec");
            Thread.sleep(30000);
            Runtime.getRuntime().exec("taskkill /F /IM Brave.exe");

        } catch (InterruptedException e) {
            System.err.println("Exceção...");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void testing() {

        System.err.println("$ LAUNCHING TESTING.");

        while (true) {

            try {
                System.err.println("A dormir 20 segundos.");
                Thread.sleep(20000);
                if (this.netIsAvailable() == true) {
                    System.err.println("Há net... Tudo bem.");
                } else {
                    System.err.println("Ligar à net FON");
                    Runtime.getRuntime().exec("netsh wlan connect ssid=NOS_WIFI_Fon name=NOS_WIFI_Fon interface=\"Wi-Fi 3\"");
                    execBrowser();
                }

            } catch (InterruptedException e) {
                System.err.println("Exceção...");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void startup() {

        System.err.println("$ LAUNCHING STARTUP.");

        try {
            if (this.netIsAvailable() == true) {
                System.err.println("Há net... Tudo bem.");
            } else {
                System.err.println("Ligar à net FON");
                Runtime.getRuntime().exec("netsh wlan connect ssid=NOS_WIFI_Fon name=NOS_WIFI_Fon interface=\"Wi-Fi 3\"");
                execBrowser();
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}