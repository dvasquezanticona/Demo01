export class RestResponseLogin{

    /**
     * EN CASO DE ERROR ESTO DEVUELVE.
    "accessToken": "Error. Bad credentials",
    "tokenType": "Bearer",
    "nombre": "Error Bro!!Bad credentials",
    "loginOK": false
     */
    public accessToken:string;
    public tokenType:string;
    public nombre:string;
    public loginOK:boolean;

}