export default class UserAuthenticationResponseDto {
  public token: string;

  constructor(data: any) {
    this.token = data.token;
  }
}