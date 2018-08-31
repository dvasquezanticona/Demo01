import { ParentEntity } from './parentEntity.model';
export class UserLogin extends ParentEntity{
    public login : string;
    public password: string;
    public password2: string;
}