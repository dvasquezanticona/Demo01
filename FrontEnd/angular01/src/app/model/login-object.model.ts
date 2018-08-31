/** 
 * Es una objeto que hemos typeado para tener más controlada 
 * la información que se enviará a la petición de login.
*/
export class LoginObject {

    public username: string;
    public password: string;


    constructor( object: any){
      this.username = (object.username) ? object.username : null;
      this.password = (object.password) ? object.password : null;
    }

  }