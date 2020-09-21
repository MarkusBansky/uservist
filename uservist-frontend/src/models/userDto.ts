import moment, {Moment} from "moment";

export default class UserDto {
  public id: number;
  public email: string;
  public username: string;
  public firstName: string;
  public lastName: string;
  public updatedAt: Moment;
  public createdAt: Moment;

  constructor(data: any) {
    this.id = data.id;
    this.email = data.email;
    this.username = data.username;
    this.firstName = data.firstName;
    this.lastName = data.lastName;
    this.updatedAt = moment(data.updatedAt);
    this.createdAt = moment(data.createdAt);
  }
}