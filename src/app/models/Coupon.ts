export class Coupon {
  public constructor(
    public id?: number,
    public companyId?: number,
    public title?: string,
    public startDate?: string,
    public endDate?: string,
    public amount?: number,
    public catagory?: string,
    public description?: string,
    public price?: number,
    public image?: string,
  ) { }
}

export interface CouponType {
  value: string;
}
