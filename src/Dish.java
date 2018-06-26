import java.awt.*;

import javax.swing.*;

public class Dish {
	private String code;
	private String name;
	private String material;
	private String method;
	private String pictureAddress;
	private String videoAddress;
	private String comment;
	private String style;
	private String promulgatorAccount;
	private String Account;
	private JTextField Code;
	private JTextField Name;
	private JTextField Style;
	private JTextField PromulgatorAccount;
	private JTextArea Material;
	private JTextArea Method;
	private JTextArea Comment1;
	private JTextArea Comment2;
	
	public String getCode(){
		return code;
	}
	
	public void setCode(String initialCode) {
		this.code=initialCode;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String initialName) {
		this.name=initialName;
	}
	
	public String getMaterial(){
		return material;
	}
	
	public void setMaterial(String initialMaterial) {
		this.material=initialMaterial;
	}
	
	public String getMethod(){
		return method;
	}
	
	public void setMethod(String initialMethod) {
		this.method=initialMethod;
	}
	
	public String getPictureAddress(){
		return pictureAddress;
	}
	
	public void setPictureAddress(String initialPictureAddress) {
		this.pictureAddress=initialPictureAddress;
	}
	
	public String getVideoAddress(){
		return videoAddress;
	}
	
	public void setVideoAddress(String initialVideoAddress) {
		this.videoAddress=initialVideoAddress;
	}
	
	public String getComment(){
		return comment;
	}
	
	public void setComment(String initialComment) {
		this.comment=initialComment;
	}
	
	public String getStyle(){
		return style;
	}
	
	public void setStyle(String initialStyle) {
		this.style=initialStyle;
	}
	
	public String getPromulgatorAccount(){
		return promulgatorAccount;
	}
	
	public void setPromulgatorAccount(String initialPromulgatorAccount) {
		this.promulgatorAccount=initialPromulgatorAccount;
	}
	
	public boolean equals(Object object) {
		return (object instanceof Dish)&&((Dish) object).getCode()==this.getCode();
	}
	
	public String toString(){
		return getCode()+" "+getName()+" "+getMaterial()+" "+getMethod()+
				" "+getPictureAddress()+" "+getVideoAddress()+" "+getComment()
				+" "+getStyle()+" "+getPromulgatorAccount();
	}
	
	public JPanel DishInfo(boolean b, String account) {
		Account=account;
		JPanel DishInfo=new JPanel(new BorderLayout());
		
		Code=new JTextField(getCode(), 17);
		Name=new JTextField(getName(), 17);
		Style=new JTextField(getStyle(), 17);
		PromulgatorAccount=new JTextField(getPromulgatorAccount(), 17);
		Material=new JTextArea(getMaterial(), 5, 27);
		Material.setLineWrap(true);
		Material.setWrapStyleWord(true);
		Method=new JTextArea(getMethod(), 5, 27);
		Method.setLineWrap(true);
		Method.setWrapStyleWord(true);
		Comment1=new JTextArea(5, 50);
		Comment1.setLineWrap(true);
		Comment1.setWrapStyleWord(true);
		Comment2=new JTextArea(5, 50);
		Comment2.setLineWrap(true);
		Comment2.setWrapStyleWord(true);
		
		String[] comment3=this.getComment().split("\\|");
		for(int i=0;i<comment3.length;i++) {
			Comment1.append(comment3[i]+"\n");
		}
		JLabel code1=new JLabel("菜品编号：",SwingConstants.RIGHT);
		JLabel name1=new JLabel("菜品名称：",SwingConstants.RIGHT);
		JLabel style1=new JLabel("菜品风格：",SwingConstants.RIGHT);
		JLabel promulgatoraccount=new JLabel("发布者：",SwingConstants.RIGHT);
		JLabel material1=new JLabel("使用材料：",SwingConstants.RIGHT);
		JLabel method1=new JLabel("制作方法：",SwingConstants.RIGHT);
		JLabel comment1=new JLabel("评论：",SwingConstants.RIGHT);
		JLabel comment2=new JLabel("发表评论：",SwingConstants.RIGHT);
		JLabel picture=new JLabel(new ImageIcon(getPictureAddress()));
		JLabel video=new JLabel(getVideoAddress());
		
		Code.setEditable(false);
		Name.setEditable(b);
		Style.setEditable(b);
		PromulgatorAccount.setEditable(false);
		Material.setEditable(b);
		Method.setEditable(b);
		Comment1.setEditable(false);
		Comment2.setEditable(true);
		
		JPanel panel1=new JPanel(new GridLayout(4,2));
		panel1.add(code1);
		panel1.add(Code);
		panel1.add(name1,"East");
		panel1.add(Name);
		panel1.add(style1,"East");
		panel1.add(Style);
		panel1.add(promulgatoraccount,"East");
		panel1.add(PromulgatorAccount);
		
		JPanel panel2=new JPanel(new GridLayout(2,2));
		panel2.add(material1,"NorthEast");
		panel2.add(Material);
		panel2.add(method1,"NorthEast");
		panel2.add(Method);
		
		JPanel panel3=new JPanel(new GridLayout(2,2));
		panel3.add(comment1,"NorthEast");
		panel3.add(Comment1);
		panel3.add(comment2,"NorthEast");
		panel3.add(Comment2);
		
		JPanel Panel1=new JPanel(new GridLayout(2,1));
		Panel1.add(panel1);
		Panel1.add(panel2);
		
		JPanel Panel2=new JPanel(new GridLayout(2,1));
		Panel2.add(picture);
		Panel2.add(video);
		
		JPanel Panel4=new JPanel();
		Panel4.add(Panel1, "Center");
		Panel4.add(Panel2, "East");
		
		DishInfo.add(Panel4, "Center");
		DishInfo.add(panel3, "South");
		
		return DishInfo;
	}
	
	public String getCode2() {
		String code2=Code.getText();
		Code.setText(code2);
		return Code.getText();
	}
	
	public String getName2() {
		String name2=Name.getText();
		Name.setText(name2);
		return Name.getText();
	}
	
	public String getStyle2() {
		String style2=Style.getText();
		Style.setText(style2);
		return Style.getText();
	}
	
	public String getPromulgatorAccount2() {
		String promulgatoraccount2=PromulgatorAccount.getText();
		PromulgatorAccount.setText(promulgatoraccount2);
		return PromulgatorAccount.getText();
	}
	
	public String getMaterial2() {
		String material2=Material.getText();
		Material.setText(material2);
		return Material.getText();
	}
	
	public String getMethod2() {
		String method2=Method.getText();
		Method.setText(method2);
		return Method.getText();
	}
	
	public String getComment2() {
		String comment2;
		if(Comment2.getText()==null) {
			comment2=this.getComment();
		}else {
			comment2=this.getComment()+"|"+Account+"："+Comment2.getText()+" ";
		}
		return comment2;
	}
	
}
