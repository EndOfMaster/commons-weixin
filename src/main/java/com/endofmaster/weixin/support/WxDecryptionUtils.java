package com.endofmaster.weixin.support;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

/**
 * @author ZM.Wang
 */
public class WxDecryptionUtils {

    private String decryption(String encryptData, String iv, String sessionKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        // 解密
        byte[] encryptDataByte = Base64.decodeBase64(encryptData);
        byte[] sessionKeyByte = Base64.decodeBase64(sessionKey);
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(Base64.decodeBase64(iv));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(sessionKeyByte, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        return new String(cipher.doFinal(encryptDataByte), StandardCharsets.UTF_8);
    }
}
