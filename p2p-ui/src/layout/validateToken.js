import axios from "axios";
import { getDecryptedSessionItem } from "../utils/sessionUtils";

export const validateToken = async () => {
    const token = getDecryptedSessionItem('token');
    const BASE_URL_AUTH = import.meta.env.VITE_AUTH_API_URL;
    if(!token) return false; // No token in sessionStorage, return false

    const sessionExpiry = getDecryptedSessionItem("app_session_expiry");
    if (sessionExpiry && Date.now() > sessionExpiry) {
        // Token is valid but session has expired
        return { valid: false, reason: "Session expired" };
    }

    try{
        const response = await axios.get(BASE_URL_AUTH+'validate', {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        });
        return response.status === 200;
    }catch(error){
        console.error('Token validation failed: ', error.response?.data || error.message);
        return false;
    }
};