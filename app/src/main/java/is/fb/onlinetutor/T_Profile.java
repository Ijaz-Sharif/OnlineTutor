package is.fb.onlinetutor;

class T_Profile {
    public String T_name;
    public String D_name;
    public String E_image;
    public String T_number;
    public String key;
    public String mail;
  public T_Profile(){

  }
  public T_Profile(String name,String degree_name,String degree,String num,String m){
    T_name=name;
    D_name=degree_name;
    E_image=degree;
    T_number=num;
    mail=m;
  }
  public String getT_name(){
      return T_name;
  }
  public String getD_name(){
      return D_name;
  }
  public String getE_image(){
      return E_image;
  }
  public String getT_number(){
      return T_number;
  }
  public void setKey( String k){
     key=k;
  }
  public String getMail(){
      return mail;
  }
  public  void setMail(String m){
      mail=m;
  }
  public String getKey(){
      return key;
  }
}
