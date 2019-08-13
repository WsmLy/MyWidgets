package com.example.myutils.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * create by sam on 2019-08-05
 * description: 加密工具类,Base64编码解码, URLEncoder编码解码, des加密解密, RSA加密解密,MD5,
 */
public class SecretUtils {
    //encodeType
    public static final String UTF8 = "UTF-8";
    public static final String UTF16 = "UTF-16";
    public static final String GBK = "GBK";
    public static final String GB2312 = "GB2312";
    public static final String UNICODE = "UNICODE";
    public static final String ASCLL = "ASCLL";

    /**
     * base64
     */
    //编码
    public static String base64Encode(String str) {
        byte[] encode = Base64.encode(str.getBytes(), Base64.DEFAULT);
        return new String(encode);
    }

    //解码
    public static String base64Decode(String result) {
        byte[] decode = Base64.decode(result, Base64.DEFAULT);
        return new String(decode);
    }

    /**
     * URLEncode
     */
    //编码
    public static String URLEncode(String string, String encodeType) {
        String encode = null;
        try {
            encode = URLEncoder.encode(string, encodeType);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encode;
    }

    //解码
    public static String URLDecode(String result, String encodeType) {
        String decode = null;
        try {
            decode = URLDecoder.decode(result, encodeType);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decode;
    }

    /**
     * DES
     */
    //加密
    public static String desEncrypt(String string, String password) {
        String result = null;
        try {
            // 初始化 设置加密解密的类型
            Cipher cipher = Cipher.getInstance("DES");
            // 初始化密码
            SecretKeySpec keySpec = new SecretKeySpec(string.getBytes(), "DES");
            try {
                //设置此次操作是加密还是解密
                cipher.init(Cipher.ENCRYPT_MODE, keySpec);
                // 加密
                final byte[] finalResult = cipher.doFinal(password.getBytes());
                //经过base64处理避免出现乱码情况
                byte[] encode = Base64.encode(finalResult, Base64.DEFAULT);
                result = new String(encode);

            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    //解密
    public static String desDecrypt(String string, String password) {
        String result = null;
        try {
            Cipher cipherDecode = Cipher.getInstance("DES");
            SecretKeySpec spec = new SecretKeySpec(password.getBytes(), "DES");
            cipherDecode.init(Cipher.DECRYPT_MODE, spec);
            // 获取textView加密后的结果
            byte[] decode = Base64.decode(string, Base64.DEFAULT);
            byte[] doFinal = cipherDecode.doFinal(decode);
            result = new String(doFinal);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * AES
     */
    //加密
    public static String aesEncrypt(String string, String password) {
        String result = null;
        try {
            // 初始化 设置加密解密的类型
            Cipher cipher = Cipher.getInstance("AES");
            // 初始化密码
            SecretKeySpec keySpec = new SecretKeySpec(password.getBytes(), "AES");
            try {
                //设置此次操作是加密还是解密
                cipher.init(Cipher.ENCRYPT_MODE, keySpec);
                // 加密
                final byte[] finalResult = cipher.doFinal(string.getBytes());
                //经过base64处理避免出现乱码情况
                byte[] encode = Base64.encode(finalResult, Base64.DEFAULT);
                result = new String(encode);
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    //解密
    public static String aesDecrypt(String string, String password) {
        String result = null;
        try {
            Cipher cipherDecode = Cipher.getInstance("AES");
            SecretKeySpec spec = new SecretKeySpec(password.getBytes(), "AES");
            cipherDecode.init(Cipher.DECRYPT_MODE, spec);
            // 获取textView加密后的结果
            byte[] decode = Base64.decode(string, Base64.DEFAULT);
            byte[] doFinal = cipherDecode.doFinal(decode);
            result = new String(doFinal);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * RSA
     */
    //生成公钥和密钥
    private static PublicKey mPublicKey;
    private static PrivateKey mPrivateKey;

    private void getRSAKey() {
        try {
            // 初始化密码对生成器
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            // 设置密码的长度
            generator.initialize(1024);
            // 生成密码对
            KeyPair keyPair = generator.generateKeyPair();
            // 获取公钥
            mPublicKey = keyPair.getPublic();
            //获取私钥
            mPrivateKey = keyPair.getPrivate();
            //////////////////////////把私钥存储String到本地//////////////////////////////
            byte[] mPrivateKeyEncoded = mPrivateKey.getEncoded();
            byte[] encode = Base64.encode(mPrivateKeyEncoded, Base64.DEFAULT);
            String key = new String(encode);
            SharedPreferences sp = ContextUtils.getContext().getSharedPreferences("rsa", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("privateKey", key);
            edit.commit();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    //加密
    public static String RSAEncrypt(String content) {
        String result = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            // 设置加密或者解密模式 ，传入公钥
            cipher.init(Cipher.ENCRYPT_MODE, mPublicKey);

            byte[] encode = cipher.doFinal(content.getBytes());
            byte[] bytes = Base64.encode(encode, Base64.DEFAULT);
            result = new String(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    //解密
    public static String RSADecrypt(String result) {
        byte[] decode = Base64.decode(result, Base64.DEFAULT);
        try {
            Cipher ciphers = Cipher.getInstance("RSA");
            ciphers.init(Cipher.DECRYPT_MODE, mPrivateKey);
            byte[] bytes = ciphers.doFinal(decode);
            result = new String(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
    //通过服务器字符串解密
    public static String RSADecryptByServer(String result) {
        try {
            // 1 通过字符串生成私钥
            SharedPreferences sharedPreferences = ContextUtils.getContext().getSharedPreferences("wenjian", Context.MODE_PRIVATE);
            String stringKey = sharedPreferences.getString("privateKey", "");
            if (TextUtils.isEmpty(stringKey)) {
                LogUtils.d("TAG", "onClick: 私钥没有存成功");
                return "";
            } else {
                LogUtils.d("TAG", "onClick: StringKey  == " + stringKey);
            }
            //因为我们存私钥的经过Base64编码过，所以我们必须解码回去
            byte[] bytesKey = Base64.decode(stringKey, Base64.DEFAULT);
            // 通过byte数组生成公钥或者私钥
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytesKey);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = factory.generatePrivate(spec);
            // 2 解密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decode = Base64.decode(result, Base64.DEFAULT);
            byte[] doFinal = cipher.doFinal(decode);
            result = new String(doFinal);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * MD5加密
     */
    public static String MD5(String raw) {
        String md5Str = raw;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(raw.getBytes());
            byte[] encryContext = md.digest();

            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < encryContext.length; offset++) {
                i = encryContext[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            md5Str = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5Str;
    }

    /**
     * SHA1
     */
    //加密
    private static String SHA1Encrypt(String content) {
        String result = null;
        //摘要
        try {
            // 初始化
            MessageDigest digest = MessageDigest.getInstance("SHA1");
            //指定 byte数组来进行摘要
            digest.update(content.getBytes());
            // 获取摘要的结果
            byte[] results = digest.digest();
            // 创建Buffer来累加字符串
            StringBuffer buffer = new StringBuffer();

            for (int i = 0; i < results.length; i++) {
                byte conResult = results[i];
                // 把得到结果 传化成16进制的字符串
                String s = Integer.toHexString(conResult & 0xFF);
                //
                if (s.length()==1){
                    buffer.append("0");
                }
                buffer.append(s);
            }
            result = buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
}
