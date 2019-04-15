import axios from 'axios';

const path = '/api/auth';

class AuthController {

    public async signin(signInRequest: SignInRequest): Promise<JwtAuthResponse> {
        const result = await axios.post<JwtAuthResponse>(path + '/signin', signInRequest);
        return result.data;
    };

    public async signup(newUserTO: NewUserTO): Promise<JwtAuthResponse> {
        const result = await axios.post<JwtAuthResponse>(path + '/signup', newUserTO);
        return result.data;
    };
}

export const authController = new AuthController();

interface UserIdentities {
    username: string,
    password: string
}

export interface NewUserTO extends UserIdentities {
}

export interface SignInRequest extends UserIdentities {
}

export interface JwtAuthResponse {
    accessToken: string,
    tokenType: string
}
