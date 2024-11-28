// sessionUtils.js
import CryptoJS from 'crypto-js';

const secretKey = "a5c43b89f9e6bd7a2b76384c6d7edc68";

export const setEncryptedSessionItem = (key, value) => {
  // Ensure the value is a string before encryption
  const encryptedValue = CryptoJS.AES.encrypt(String(value), secretKey).toString();
  sessionStorage.setItem(key, encryptedValue);
};

export const getDecryptedSessionItem = (key) => {
    const encryptedValue = sessionStorage.getItem(key);
    if (!encryptedValue) return null;
  
    try {
      const bytes = CryptoJS.AES.decrypt(encryptedValue, secretKey);
      const decryptedValue = bytes.toString(CryptoJS.enc.Utf8);
  
      // Return the decrypted value only if it's a valid UTF-8 string
      return decryptedValue ? decryptedValue : null;
    } catch (e) {
      console.error('Decryption failed:', e);
      return null;  // Return null in case of an error
    }
  };
  