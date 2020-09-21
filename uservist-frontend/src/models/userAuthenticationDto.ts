export default class UserAuthenticationDto {
  public username: string;
  public password: string;
  public browser: string;
  public ipAddress: string;
  public service: string;

  constructor(data: any) {
    this.username = data.username;
    this.password = data.password;
    this.browser = data.browser;
    this.ipAddress = data.ipAddress;
    this.service = data.service;
  }
}