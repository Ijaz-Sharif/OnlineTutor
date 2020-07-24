package is.fb.onlinetutor;

 public class Online {
     private String name,subject,education,fee,time,mail,number;
     public Online(){

     }
     public Online(String name,String subject,String education,String fee,String time,String mail,String number){
         this.name=name;
         this.subject=subject;
         this.education=education;
         this.fee=fee;
         this.time=time;
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
     public String getMail(){
         return mail;
     }
     public String getNumber(){
         return number;
     }
}
