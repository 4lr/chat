import axios from 'axios';

const path = '/api/auth';

class AuthController {

    async signin(signInRequest) {
        const result = await axios.post<JwtAuthResponse>(path + '/signin', signInRequest);
        return result.data;
    };

    async signup(newUserTO) {
        const result = await axios.post<JwtAuthResponse>(path + '/signup', newUserTO);
        return result.data;
    };
}

export const authController = new AuthController();
