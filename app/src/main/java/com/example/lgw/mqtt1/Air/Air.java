package com.example.lgw.mqtt1.Air;

import android.util.Log;

import java.util.ArrayList;

public  class Air {
	//	String[] t= {"制热","开机","一级","扫风","睡眠","温度","睡眠定时","超强","灯光","健康",
	//			"干燥/辅热","换气","上下扫风","左右扫风","显示温度","温度"};
	String[] t= {"制热","开机","1","无","无","25","0","普通","灭","无",
			"无","无","无","无","显示","25"};
	public  ArrayList<String> list = new ArrayList<String>();

	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	String dataValue;
	public String[] getT() {
		return t;
	}

	public void setT(String[] t) {
		this.t = t;
	}
	public String S(){
		dataValue = "Start：";
		list.clear();
		list.add(0, "{\"IR_CODE\":\"9000,4500");

	/*模式*/
		if(t[0]=="制热") {
			list.add(1,"560,560,560,560,560,1690");
			dataValue += "t[0]制热";
		}
		else if(t[0]=="送风") {
			list.add(1,"560,1690,560,1690,560,560");
			dataValue += "t[0]送风";
		}
		else if(t[0]=="自动") {
			list.add(1,"560,560,560,560,560,560");
			dataValue += "t[0]自动";
		}
		else if(t[0]=="除湿") {
			list.add(1,"560,560,560,1690,560,560");
			dataValue += "t[0]除湿";
		}
		else if(t[0]=="制冷") {
			list.add(1,"560,1690,560,560,560,560");
			dataValue += "t[0]制冷";
		}
	/*开关机*/
		switch(t[1]){
			case "开机":
				list.add(2,"560,1690");dataValue += "t[1]开机";break;
			case "关机":
				list.add(2,"560,560");dataValue += "t[1]关机";break;
			default:
				System.out.println("erro");break;
		}
	/*风速*/
		switch(t[2]){
			case "1":
				list.add(3,"560,1690,560,560");dataValue += "t[2]一级";break;

			case "2":
				list.add(3,"560,560,560,1690");dataValue += "t[2]二级";break;

			case "3":
				list.add(3,"560,1690,560,1690");dataValue += "t[2]三级";break;

			case "4":
				list.add(3,"560,560,560,560");dataValue += "t[2]自动";break;

			default:
				System.out.println("erro");break;
		}
	/*有无扫风*/
		switch(t[3]){
			case "有":
				list.add(4,"560,1690");dataValue += "t[3]有";break;
			case "无":
				list.add(4,"560,560");dataValue += "t[3]无";break;
			default:
				System.out.println("erro");break;
		}
	/*是否睡眠*/
		switch(t[4]){
			case "有":
				list.add(5,"560,1690");dataValue += "t[4]有";break;
			case "无":
				list.add(5,"560,560");dataValue += "t[4]无";break;
			default:
				System.out.println("erro");break;
		}
	/*温度*/
		switch(t[5]){
			case "30":
				list.add(6,"560,560,560,1690,560,1690,560,1690");dataValue += "t[5]30";break;
			case "29":
				list.add(6,"560,1690,560,560,560,1690,560,1690");dataValue += "t[5]29";break;
			case "28":
				list.add(6,"560,560,560,560,560,1690,560,1690");dataValue += "t[5]28";break;
			case "27":
				list.add(6,"560,1690,560,1690,560,560,560,1690");dataValue += "t[5]27";break;
			case "26":
				list.add(6,"560,560,560,1690,560,560,560,1690");dataValue += "t[5]26";break;
			case "25":
				list.add(6,"560,1690,560,560,560,560,560,1690");dataValue += "t[5]25";break;
			case "24":
				list.add(6,"560,560,560,560,560,560,560,1690");dataValue += "t[5]24";break;
			case "23":
				list.add(6,"560,1690,560,1690,560,1690,560,560");dataValue += "t[5]23";break;
			case "22":
				list.add(6,"560,560,560,1690,560,1690,560,560");dataValue += "t[5]22";break;
			case "21":
				list.add(6,"560,1690,560,560,560,1690,560,560");dataValue += "t[5]21";break;
			case "20":
				list.add(6,"560,560,560,560,560,1690,560,560");dataValue += "t[5]20";break;
			case "19":
				list.add(6,"560,1690,560,1690,560,560,560,560");dataValue += "t[5]19";break;
			case "18":
				list.add(6,"560,560,560,1690,560,560,560,560");dataValue += "t[5]18";break;
			case "17":
				list.add(6,"560,1690,560,560,560,560,560,560");dataValue += "t[5]17";break;
			case "16":
				list.add(6,"560,560,560,560,560,560,560,560");dataValue += "t[5]16";break;
			default:
				System.out.println("erro");break;
		}
	/*睡眠定时*/
		switch(t[6]){
			case "0":
				list.add(7,"560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560");dataValue += "t[6]0";break;
			case "0.5":
				list.add(7,"560,1690,560,560,560,560,560,1690,560,560,560,560,560,560,560,560");dataValue += "t[6]0.5";break;
			case "1":
				list.add(7,"560,560,560,560,560,560,560,1690,560,1690,560,560,560,560,560,560");dataValue += "t[6]1";break;
			case "1.5":
				list.add(7,"560,1690,560,560,560,560,560,1690,560,1690,560,560,560,560,560,560");dataValue += "t[6]1.5";break;
			case "2":
				list.add(7,"560,560,560,560,560,560,560,1690,560,560,560,1690,560,560,560,560");dataValue += "t[6]2";break;
			case "2.5":
				list.add(7,"560,1690,560,560,560,560,560,1690,560,560,560,1690,560,560,560,560");dataValue += "t[6]2.5";break;
			case "3":
				list.add(7,"560,560,560,560,560,560,560,1690,560,1690,560,1690,560,560,560,560");dataValue += "t[6]3";break;
			case "3.5":
				list.add(7,"560,1690,560,560,560,560,560,1690,560,1690,560,1690,560,560,560,560");dataValue += "t[6]3.5";break;
			case "4":
				list.add(7,"560,560,560,560,560,560,560,1690,560,560,560,560,560,1690,560,560");dataValue += "t[6]4";break;
			case "4.5":
				list.add(7,"560,1690,560,560,560,560,560,1690,560,560,560,560,560,1690,560,560");dataValue += "t[6]4.5";break;
			case "5":
				list.add(7,"560,560,560,560,560,560,560,1690,560,1690,560,560,560,1690,560,560");dataValue += "t[6]5";break;
			case "5.5":
				list.add(7,"560,1690,560,560,560,560,560,1690,560,1690,560,560,560,1690,560,560");dataValue += "t[6]5.5";break;
			case "6":
				list.add(7,"560,560,560,560,560,560,560,1690,560,560,560,1690,560,1690,560,560");dataValue += "t[6]6";break;
			case "6.5":
				list.add(7,"560,1690,560,560,560,560,560,1690,560,560,560,1690,560,1690,560,560");dataValue += "t[6]6.5";break;
			case "7":
				list.add(7,"560,560,560,560,560,560,560,1690,560,1690,560,1690,560,1690,560,560");dataValue += "t[6]7";break;
			case "7.5":
				list.add(7,"560,1690,560,560,560,560,560,1690,560,1690,560,1690,560,1690,560,560");dataValue += "t[6]7.5";break;
			case "8":
				list.add(7,"560,560,560,560,560,560,560,1690,560,560,560,560,560,560,560,1690");dataValue += "t[6]8";break;
			case "8.5":
				list.add(7,"560,1690,560,560,560,560,560,1690,560,560,560,560,560,560,560,1690");dataValue += "t[6]8.5";break;
			case "9":
				list.add(7,"560,560,560,560,560,560,560,1690,560,1690,560,560,560,560,560,1690");dataValue += "t[6]9";break;
			case "9.5":
				list.add(7,"560,1690,560,560,560,560,560,1690,560,1690,560,560,560,560,560,1690");dataValue += "t[6]9.5";break;
			case "10":
				list.add(7,"560,560,560,1690,560,560,560,1690,560,560,560,560,560,560,560,560");dataValue += "t[6]10";break;
			case "10.5":
				list.add(7,"560,1690,560,1690,560,560,560,1690,560,560,560,560,560,560,560,560");dataValue += "t[6]10.5";break;
			case "11":
				list.add(7,"560,560,560,1690,560,560,560,1690,560,1690,560,560,560,560,560,560");dataValue += "t[6]11";break;
			case "11.5":
				list.add(7,"560,1690,560,1690,560,560,560,1690,560,1690,560,560,560,560,560,560");dataValue += "t[6]11.5";break;
			case "12":
				list.add(7,"560,560,560,1690,560,560,560,1690,560,560,560,1690,560,560,560,560");dataValue += "t[6]12";break;
			case "12.5":
				list.add(7,"560,1690,560,1690,560,560,560,1690,560,560,560,1690,560,560,560,560");dataValue += "t[6]12.5";break;
			case "13":
				list.add(7,"560,560,560,1690,560,560,560,1690,560,1690,560,1690,560,560,560,560");dataValue += "t[6]13";break;
			case "13.5":
				list.add(7,"560,1690,560,1690,560,560,560,1690,560,1690,560,1690,560,560,560,560");dataValue += "t[6]13.5";break;
			case "14":
				list.add(7,"560,560,560,1690,560,560,560,1690,560,560,560,560,560,1690,560,560");dataValue += "t[6]14";break;
			case "14.5":
				list.add(7,"560,1690,560,1690,560,560,560,1690,560,560,560,560,560,1690,560,560");dataValue += "t[6]14.5";break;
			case "15":
				list.add(7,"560,560,560,1690,560,560,560,1690,560,1690,560,560,560,1690,560,560");dataValue += "t[6]15";break;
			case "15.5":
				list.add(7,"560,1690,560,1690,560,560,560,1690,560,1690,560,560,560,1690,560,560");dataValue += "t[6]15.5";break;
			case "16":
				list.add(7,"560,560,560,1690,560,560,560,1690,560,560,560,1690,560,1690,560,560");dataValue += "t[6]16";break;
			case "16.5":
				list.add(7,"560,1690,560,1690,560,560,560,1690,560,560,560,1690,560,1690,560,560");dataValue += "t[6]16.5";break;
			case "17":
				list.add(7,"560,560,560,1690,560,560,560,1690,560,1690,560,1690,560,1690,560,560");dataValue += "t[6]17";break;
			case "17.5":
				list.add(7,"560,1690,560,1690,560,560,560,1690,560,1690,560,1690,560,1690,560,560");dataValue += "t[6]17.5";break;
			case "18":
				list.add(7,"560,560,560,1690,560,560,560,1690,560,560,560,560,560,560,560,1690");dataValue += "t[6]18";break;
			case "18.5":
				list.add(7,"560,1690,560,1690,560,560,560,1690,560,560,560,560,560,560,560,1690");dataValue += "t[6]18.5";break;
			case "19":
				list.add(7,"560,560,560,1690,560,560,560,1690,560,1690,560,560,560,560,560,1690");dataValue += "t[6]19";break;
			case "19.5":
				list.add(7,"560,1690,560,1690,560,560,560,1690,560,1690,560,560,560,560,560,1690");dataValue += "t[6]19.5";break;
			case "20":
				list.add(7,"560,560,560,560,560,1690,560,1690,560,560,560,560,560,560,560,560");dataValue += "t[6]20";break;
			case "20.5":
				list.add(7,"560,1690,560,560,560,1690,560,1690,560,560,560,560,560,560,560,560");dataValue += "t[6]20.5";break;
			case "21":
				list.add(7,"560,560,560,560,560,1690,560,1690,560,1690,560,560,560,560,560,560");dataValue += "t[6]21";break;
			case "21.5":
				list.add(7,"560,1690,560,560,560,1690,560,1690,560,1690,560,560,560,560,560,560");dataValue += "t[6]21.5";break;
			case "22":
				list.add(7,"560,560,560,560,560,1690,560,1690,560,560,560,1690,560,560,560,560");dataValue += "t[6]22";break;
			case "22.5":
				list.add(7,"560,1690,560,560,560,1690,560,1690,560,560,560,1690,560,560,560,560");dataValue += "t[6]22.5";break;
			case "23":
				list.add(7,"560,560,560,560,560,1690,560,1690,560,1690,560,1690,560,560,560,560");dataValue += "t[6]23";break;
			case "23.5":
				list.add(7,"560,1690,560,560,560,1690,560,1690,560,1690,560,1690,560,560,560,560");dataValue += "t[6]23.5";break;
			case "24":
				list.add(7,"560,560,560,560,560,1690,560,1690,560,560,560,560,560,1690,560,560");dataValue += "t[6]24";break;
			default:
				System.out.println("erro");break;
		}
	/*超强或普通*/
		switch(t[7]){
			case "超强":
				list.add(8,"560,1690");dataValue += "t[7]超强";break;
			case "普通":
				list.add(8,"560,560");dataValue += "t[7]普通";break;
			default:
				System.out.println("erro");break;
		}
	/*灯光灭暗*/
		switch(t[8]){
			case "灭":
				list.add(9,"560,1690");dataValue += "t[8]灭";break;
			case "亮":
				list.add(9,"560,560");dataValue += "t[8]亮";break;
			default:
				System.out.println("erro");break;
		}
	/*健康*/
		switch(t[9]){
			case "健康":
				list.add(10,"560,1690");dataValue += "t[9]健康";break;
			case "无":
				list.add(10,"560,560");dataValue += "t[9]无";break;
			default:
				System.out.println("erro");break;
		}
	/*制冷-干燥/制热-辅热*/
		switch(t[10]){
			case "干燥":
				list.add(11,"560,1690");dataValue += "t[10]干燥";break;
			case "无":
				list.add(11,"560,560");dataValue += "t[10]无";break;
			default:
				System.out.println("erro");break;
		}
	/*换气*/
		switch(t[11]){
			case "换气":
				list.add(12,"560,1690");dataValue += "t[11]换气";break;
			case "无":
				list.add(12,"560,560");dataValue += "t[11]无";break;
			default:
				System.out.println("erro");break;
		}
	/*地址码*/
		list.add(13,"560,560,560,560,560,560,560,1690,560,560,560,1690,560,560,560,560,560,1690,560,560,560,20000");
	/*上下扫风*/
		switch(t[12]){
			case "有":
				list.add(14,"560,1690");dataValue += "t[12]有";break;
			case "无":
				list.add(14,"560,560");dataValue += "t[12]无";break;
			default:
				System.out.println("erro");break;
		}
		list.add(15,"560,560,560,560,560,560");
	/*左右扫风*/
		switch(t[13]){
			case "有":
				list.add(16,"560,1690");dataValue += "t[13]有";break;
			case "无":
				list.add(16,"560,560");dataValue += "t[13]无";break;
			default:
				System.out.println("erro");break;
		}
		list.add(17,"560,560,560,560,560,560");
		switch(t[14]){
			case "不显示":
				list.add(18,"560,560,560,560");dataValue += "t[14]不显示";break;
			case "显示":
				list.add(18,"560,1690,560,560");dataValue += "t[14]显示";break;
			case "显示室内":
				list.add(18,"560,560,560,1690");dataValue += "t[14]显示室内";break;
			case "显示室外":
				list.add(18,"560,1690,560,1690");dataValue += "t[14]显示室外";break;
			default:
				System.out.println("erro");break;
		}
		list.add(19,"560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560");
		list.add(20,"560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560");

		/****************************************温度******************************************/
		if(t[0]=="制冷") {
    	/*温度*/
			switch(t[15]){
				case "30":
					list.add(21,"560,1690,560,560,560,560,560,560");dataValue += "t[15]制冷30";break;
				case "29":
					list.add(21,"560,560,560,560,560,560,560,560");dataValue += "t[15]制冷29";break;
				case "28":
					list.add(21,"560,1690,560,1690,560,1690,560,1690");dataValue += "t[15]制冷28";break;
				case "27":
					list.add(21,"560,560,560,1690,560,1690,560,1690");dataValue += "t[15]制冷27";break;
				case "26":
					list.add(21,"560,1690,560,560,560,1690,560,1690");dataValue += "t[15]制冷26";break;
				case "25":
					list.add(21,"560,560,560,560,560,1690,560,1690");dataValue += "t[15]制冷25";break;
				case "24":
					list.add(21,"560,1690,560,1690,560,560,560,1690");dataValue += "t[15]制冷24";break;
				case "23":
					list.add(21,"560,560,560,1690,560,560,560,1690");dataValue += "t[15]制冷23";break;
				case "22":
					list.add(21,"560,1690,560,560,560,560,560,1690");dataValue += "t[15]制冷22";break;
				case "21":
					list.add(21,"560,560,560,560,560,560,560,1690");dataValue += "t[15]制冷21";break;
				case "20":
					list.add(21,"560,1690,560,1690,560,1690,560,560");dataValue += "t[15]制冷20";break;
				case "19":
					list.add(21,"560,560,560,1690,560,1690,560,560");dataValue += "t[15]制冷19";break;
				case "18":
					list.add(21,"560,1690,560,560,560,1690,560,560");dataValue += "t[15]制冷18";break;
				case "17":
					list.add(21,"560,560,560,560,560,1690,560,560");dataValue += "t[15]制冷17";break;
				case "16":
					list.add(21,"560,1690,560,1690,560,560,560,560");dataValue += "t[15]制冷16";break;
				default:
					System.out.println("erro");break;
			}
		}
		if(t[0]=="除湿") {
    	/*温度*/
			switch(t[15]){
				case "30":
					list.add(21,"560,560,560,1690,560,560,560,560");dataValue += "t[15]除湿30";break;
				case "29":
					list.add(21,"560,1690,560,560,560,560,560,560");dataValue += "t[15]除湿29";break;
				case "28":
					list.add(21,"560,560,560,560,560,560,560,560");dataValue += "t[15]除湿28";break;
				case "27":
					list.add(21,"560,1690,560,1690,560,1690,560,1690");dataValue += "t[15]除湿27";break;
				case "26":
					list.add(21,"560,560,560,1690,560,1690,560,1690");dataValue += "t[15]除湿26";break;
				case "25":
					list.add(21,"560,1690,560,560,560,1690,560,1690");dataValue += "t[15]除湿25";break;
				case "24":
					list.add(21,"560,560,560,560,560,1690,560,1690");dataValue += "t[15]除湿24";break;
				case "23":
					list.add(21,"560,1690,560,1690,560,560,560,1690");dataValue += "t[15]除湿23";break;
				case "22":
					list.add(21,"560,560,560,1690,560,560,560,1690");dataValue += "t[15]除湿22";break;
				case "21":
					list.add(21,"560,1690,560,560,560,560,560,1690");dataValue += "t[15]除湿21";break;
				case "20":
					list.add(21,"560,560,560,560,560,560,560,1690");dataValue += "t[15]除湿20";break;
				case "19":
					list.add(21,"560,1690,560,1690,560,1690,560,560");dataValue += "t[15]除湿19";break;
				case "18":
					list.add(21,"560,560,560,1690,560,1690,560,560");dataValue += "t[15]除湿18";break;
				case "17":
					list.add(21,"560,1690,560,560,560,1690,560,560");dataValue += "t[15]除湿17";break;
				case "16":
					list.add(21,"560,560,560,560,560,1690,560,560");dataValue += "t[15]除湿16";break;
				default:
					System.out.println("erro");break;
			}
		}

		if(t[0]=="送风") {
    	/*温度*/
			switch(t[15]){
				case "30":
					list.add(21,"560,1690,560,1690,560,560,560,560");dataValue += "t[15]送风30";break;
				case "29":
					list.add(21,"560,560,560,1690,560,560,560,560");dataValue += "t[15]送风29";break;
				case "28":
					list.add(21,"560,1690,560,560,560,560,560,560");dataValue += "t[15]送风28";break;
				case "27":
					list.add(21,"560,560,560,560,560,560,560,560");dataValue += "t[15]送风27";break;
				case "26":
					list.add(21,"560,1690,560,1690,560,1690,560,1690");dataValue += "t[15]送风26";break;
				case "25":
					list.add(21,"560,560,560,1690,560,1690,560,1690");dataValue += "t[15]送风25";break;
				case "24":
					list.add(21,"560,1690,560,560,560,1690,560,1690");dataValue += "t[15]送风24";break;
				case "23":
					list.add(21,"560,560,560,560,560,1690,560,1690");dataValue += "t[15]送风23";break;
				case "22":
					list.add(21,"560,1690,560,1690,560,560,560,1690");dataValue += "t[15]送风22";break;
				case "21":
					list.add(21,"560,560,560,1690,560,560,560,1690");dataValue += "t[15]送风21";break;
				case "20":
					list.add(21,"560,1690,560,560,560,560,560,1690");dataValue += "t[15]送风20";break;
				case "19":
					list.add(21,"560,560,560,560,560,560,560,1690");dataValue += "t[15]送风19";break;
				case "18":
					list.add(21,"560,1690,560,1690,560,1690,560,560");dataValue += "t[15]送风18";break;
				case "17":
					list.add(21,"560,560,560,1690,560,1690,560,560");dataValue += "t[15]送风17";break;
				case "16":
					list.add(21,"560,1690,560,560,560,1690,560,560");dataValue += "t[15]送风16";break;
				default:
					System.out.println("erro");break;
			}
		}
		if(t[0]=="制热") {
    	/*温度*/
			switch(t[15]){
				case "30":
					list.add(21,"560,560,560,560,560,1690,560,560");dataValue += "t[15]制热30";break;
				case "29":
					list.add(21,"560,1690,560,1690,560,560,560,560");dataValue += "t[15]制热29";break;
				case "28":
					list.add(21,"560,560,560,1690,560,560,560,560");dataValue += "t[15]制热28";break;
				case "27":
					list.add(21,"560,1690,560,560,560,560,560,560");dataValue += "t[15]制热27";break;
				case "26":
					list.add(21,"560,560,560,560,560,560,560,560");dataValue += "t[15]制热26";break;
				case "25":
					list.add(21,"560,1690,560,1690,560,1690,560,1690");dataValue += "t[15]制热25";break;
				case "24":
					list.add(21,"560,560,560,1690,560,1690,560,1690");dataValue += "t[15]制热24";break;
				case "23":
					list.add(21,"560,1690,560,560,560,1690,560,1690");dataValue += "t[15]制热23";break;
				case "22":
					list.add(21,"560,560,560,560,560,1690,560,1690");dataValue += "t[15]制热22";break;
				case "21":
					list.add(21,"560,1690,560,1690,560,560,560,1690");dataValue += "t[15]制热21";break;
				case "20":
					list.add(21,"560,560,560,1690,560,560,560,1690");dataValue += "t[15]制热20";break;
				case "19":
					list.add(21,"560,1690,560,560,560,560,560,1690");dataValue += "t[15]制热19";break;
				case "18":
					list.add(21,"560,560,560,560,560,560,560,1690");dataValue += "t[15]制热18";break;
				case "17":
					list.add(21,"560,1690,560,1690,560,1690,560,560");dataValue += "t[15]制热17";break;
				case "16":
					list.add(21,"560,560,560,1690,560,1690,560,560");dataValue += "t[15]制热16";break;
				default:
					System.out.println("erro");break;
			}
		}

		list.add(22,"560,560\"}");
		//System.out.println("The final contents of the arraylist are: " + );
		Log.i("test",dataValue);
		return list.toString().replace(" ","").replace("[","").replace("]","");

	}
}



