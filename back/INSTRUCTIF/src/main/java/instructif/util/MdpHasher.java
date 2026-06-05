/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instructif.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author aessalhi
 */
public class MdpHasher {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encoder(String motDePasse) {
        return encoder.encode(motDePasse);
    }
    
    public static boolean verifier(String mdp, String mdpEncode) {
        return encoder.matches(mdp, mdpEncode);
    } 
}
