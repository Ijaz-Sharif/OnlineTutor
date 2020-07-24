package is.fb.onlinetutor;

public class Offline {
   private String name,subject,education,fee,time,area,mail,number;
    public Offline(){

    }
   public Offline(String name,String subject,String education,String fee,String time,String area,String mail,String number){
       this.name=name;
       this.subject=subject;
       this.education=education;
       this.fee=fee;
       this.time=time;
       this.area=area;
       this.mail=mail;
       this.number=number;
   }
   public String getName(){
        return name;
   }
   public String getSubject()
   {
       return subject;
   }
   public String getEducation(){
        return education;
   }
   public String getFee(){
        return fee;
   }
   public String getTime(){
        return time;
   }
   public String getArea(){
        return area;
   }
   public String getMail(){
        return mail;
   }
   public String getNumber(){
        return number;
   }
}
