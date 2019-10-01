import {Payment} from "./payment";

export class User {

  public  id? : number;
  public surname : string;
  public othername : string;
  public matric : string;
  public department : string;
  public school : string;
  public level : string;
  public ses : string
  public email : string;
  public phone : string;
  public ref : string;
  public transactionId : string;
  public status : string;
  public date : string;
  public amount : number;
  public fee : number;
  public total : number;
  public payments : Payment[];
}
