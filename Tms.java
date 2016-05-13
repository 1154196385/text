package com.briup.ch12;

import java.util.Scanner;
public class Tms{
	private Teacher[] Tcs;
  	private int index;
   public Tms(){
		Tcs = new Teacher[3];
		index = 0;
	}


	public void save(Teacher Tc){
			if(index >= Tcs.length){
			Teacher[] demo = new Teacher[Tcs.length + 3];
			System.arraycopy(Tcs,0,demo,0,index);
			Tcs = demo;
		}
		Tcs[index++] = Tc;
	}

	public Teacher[] queryAll(){
		Teacher[] demo = new Teacher[index];
		System.arraycopy(Tcs,0,demo,0,index);
		return demo;
	}

	public Teacher queryById(long id){
		int num = getIndexById(id);
		return num == -1?null:Tcs[num];
	}
	private int getIndexById(long id){
		int num = -1;
		for(int i=0;i<index;i++){
			if(Tcs[i].getId() == id){
				num = i;
				break;
			}
		}
		return num;
	}
	
	public void update(Teacher newTc){
		for(int i=0;i<index;i++){
			if(newTc.getId() == Tcs[i].getId()){
				Tcs[i].setName(newTc.getName());
				Tcs[i].setAge(newTc.getAge());
			}
		}
	}
	public void deleteById(long id){
		int num = getIndexById(id);
		for(int i=num;i<index-1;i++){
			Tcs[i] = Tcs[i+1];
		}
		Tcs[--index] = null;
	}
	
	public void menu(){
		System.out.println("********教师管理系统********");
		System.out.println("*1. 查询所有教师信息");
		System.out.println("*2. 录入教师信息");
		System.out.println("*3. 删除教师信息");
		System.out.println("*4. 查询单个教师信息");
		System.out.println("*5. 修改教师信息");
		System.out.println("*exit. 退出");
		System.out.println("*help. 帮助");
		System.out.println("****************************");
	}
	
	public static void main(String[] args){
		Tms tms = new Tms();
		tms.menu();
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.print("请输入功能编号：");
			String option = sc.nextLine();
			switch(option){
				case "1":
					System.out.println("以下是教师的信息：");
					Teacher[] arr =tms.queryAll();
					for(int i=0;i<arr.length;i++){
						System.out.println(arr[i]);
					}
					System.out.println("总计 "+tms.index+" 个");
					break;
				case "2":
					while(true){
						System.out.println("请输入教师信息【id#name#age】或者输入【break】返回上一级目录");
						String TcStr = sc.nextLine();
						if(TcStr.equals("break")){
							break;
						}
						String[] TcArr = TcStr.split("#");
						long id = Long.parseLong(TcArr[0]);
						String name = TcArr[1];
						int age = Integer.parseInt(TcArr[2]);
						Teacher Tc = new Teacher(id,name,age);
						tms.save(Tc);
						System.out.println("保存成功！");
					}
					
					break;
				case "3":
					while(true){
						System.out.println("请输入要删除教师的教师编号或者输入【break】返回上一级目录");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
		
						long id = Long.parseLong(idStr);
					    Teacher oldTc = tms.queryById(id);
						if(oldTc == null){
							System.out.println("您要删除的教师不存在！");
							continue;
						}
						tms.deleteById(id);
						System.out.println("删除成功！");
					}
					break;
				case "4":
					while(true){
						System.out.println("请输入教师编号或者输入【break】返回上一级目录");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
			
						long id = Long.parseLong(idStr);
						Teacher Tc = tms.queryById(id);
						System.out.println(Tc==null?"sorry,not found!":Tc);
					}
					break;
				case "5":
					while(true){
						System.out.println("请输入要修改老师的教师编码或者输入【break】返回上一级目录");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						long id = Long.parseLong(idStr);
						Teacher oldTc = tms.queryById(id);
						if(oldTc == null){
							System.out.println("您要修改的老师不存在！");
							continue;
						}
						System.out.println("原有信息为："+oldTc);
						System.out.println("请输入信息【name#age】");
						String newStr = sc.nextLine();
						String[] newArr = newStr.split("#");
						String name = newArr[0];
						int age = Integer.parseInt(newArr[1]);

						Teacher newTc = new Teacher(id,name,age);
						tms.update(newTc);
						System.out.println("修改成功");
					}
					break;
				case "exit":
					System.out.println("bye bye,欢迎再次使用！");
					System.exit(0);
				case "help":
					tms.menu();
					break;
				default:
					System.out.println("不合法输入！");

			}
		}
	}
  }

