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
		System.out.println("********��ʦ����ϵͳ********");
		System.out.println("*1. ��ѯ���н�ʦ��Ϣ");
		System.out.println("*2. ¼���ʦ��Ϣ");
		System.out.println("*3. ɾ����ʦ��Ϣ");
		System.out.println("*4. ��ѯ������ʦ��Ϣ");
		System.out.println("*5. �޸Ľ�ʦ��Ϣ");
		System.out.println("*exit. �˳�");
		System.out.println("*help. ����");
		System.out.println("****************************");
	}
	
	public static void main(String[] args){
		Tms tms = new Tms();
		tms.menu();
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.print("�����빦�ܱ�ţ�");
			String option = sc.nextLine();
			switch(option){
				case "1":
					System.out.println("�����ǽ�ʦ����Ϣ��");
					Teacher[] arr =tms.queryAll();
					for(int i=0;i<arr.length;i++){
						System.out.println(arr[i]);
					}
					System.out.println("�ܼ� "+tms.index+" ��");
					break;
				case "2":
					while(true){
						System.out.println("�������ʦ��Ϣ��id#name#age���������롾break��������һ��Ŀ¼");
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
						System.out.println("����ɹ���");
					}
					
					break;
				case "3":
					while(true){
						System.out.println("������Ҫɾ����ʦ�Ľ�ʦ��Ż������롾break��������һ��Ŀ¼");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
		
						long id = Long.parseLong(idStr);
					    Teacher oldTc = tms.queryById(id);
						if(oldTc == null){
							System.out.println("��Ҫɾ���Ľ�ʦ�����ڣ�");
							continue;
						}
						tms.deleteById(id);
						System.out.println("ɾ���ɹ���");
					}
					break;
				case "4":
					while(true){
						System.out.println("�������ʦ��Ż������롾break��������һ��Ŀ¼");
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
						System.out.println("������Ҫ�޸���ʦ�Ľ�ʦ����������롾break��������һ��Ŀ¼");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						long id = Long.parseLong(idStr);
						Teacher oldTc = tms.queryById(id);
						if(oldTc == null){
							System.out.println("��Ҫ�޸ĵ���ʦ�����ڣ�");
							continue;
						}
						System.out.println("ԭ����ϢΪ��"+oldTc);
						System.out.println("��������Ϣ��name#age��");
						String newStr = sc.nextLine();
						String[] newArr = newStr.split("#");
						String name = newArr[0];
						int age = Integer.parseInt(newArr[1]);

						Teacher newTc = new Teacher(id,name,age);
						tms.update(newTc);
						System.out.println("�޸ĳɹ�");
					}
					break;
				case "exit":
					System.out.println("bye bye,��ӭ�ٴ�ʹ�ã�");
					System.exit(0);
				case "help":
					tms.menu();
					break;
				default:
					System.out.println("���Ϸ����룡");

			}
		}
	}
  }

