package com.hanrui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class DatePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	//年月日的下拉框
	private JComboBox<Integer> yearBox;
	private JComboBox<Integer> monthBox;
	private JComboBox<Integer> dayBox;

	public DatePanel() {
		setSize(150, 30);
		yearBox = new JComboBox<Integer>();
		monthBox = new JComboBox<Integer>();
		dayBox = new JComboBox<Integer>();
		for (int i = 1900; i <= 2099; i++) {
			yearBox.addItem(i);
		}
		for (int i = 1; i <= 12; i++) {
			monthBox.addItem(i);
		}
		for (int i = 1; i <= 31; i++) {
			dayBox.addItem(i);
		}
		setLayout(null);
		yearBox.setBounds(0, 0, 60, 25);
		monthBox.setBounds(60, 0, 45, 25);
		dayBox.setBounds(105, 0, 45, 25);
		//为年下拉框添加点击事件
		yearBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int year=(int) yearBox.getSelectedItem();
				int month = (int) monthBox.getSelectedItem();
				int dayCount = dayBox.getItemCount();
				//当点击年下拉框的时候，先判断月份是否为2月
				if(month==2){
					//如果是2月，再判断该月的天数有多少天，如果是28天，再判断是否是闰年，如果是闰年，则加上第29天
					if(dayCount==28){
						if(isLeapYear(year)){
							dayBox.addItem(29);
						}
					}else{//如果是29天，再判断是否是闰年，如果不是闰年，则减去第29天
						if(!isLeapYear(year)){
							dayBox.removeItem(29);
						}
					}
				}
			}
		});

		monthBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedItem = (int) monthBox.getSelectedItem();
				int dayCount = dayBox.getItemCount();
				//判断选择是第几月
				switch (selectedItem) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					//如果该月天数是31天，则不需要改变，否则就相应加上所需天数
					if (dayCount == 31) {
						return;
					} else if (dayCount == 30) {
						dayBox.addItem(31);
					} else if (dayCount == 29) {
						dayBox.addItem(30);
						dayBox.addItem(31);
					} else if (dayCount == 28) {
						dayBox.addItem(29);
						dayBox.addItem(30);
						dayBox.addItem(31);
					}
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					if (dayCount == 31) {
						dayBox.removeItem(31);
					} else if (dayCount == 30) {
						return;
					} else if (dayCount == 29) {
						dayBox.addItem(30);
					} else if (dayCount == 28) {
						dayBox.addItem(29);
						dayBox.addItem(30);
					}
					break;
				case 2:
					if (dayCount == 31) {
						dayBox.removeItem(31);
						dayBox.removeItem(30);
					} else if (dayCount == 30) {
						dayBox.removeItem(30);
					} else if (dayCount == 29) {
					}
					int year = (int) yearBox.getSelectedItem();
					if (!isLeapYear(year)) {
						dayBox.removeItem(29);
					}
					break;
				}
			}
		});

		add(yearBox);
		add(monthBox);
		add(dayBox);
	}

	/**
	 * 判断是否是闰年
	 * @param year	要判断的年份
	 * @return
	 */
	private boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
	}
	
	/**
	 * 获取年月日，如2015-6-9
	 * @return
	 */
	public String getText(){
		int year=(int) yearBox.getSelectedItem();
		int month=(int) monthBox.getSelectedItem();
		int day=(int) dayBox.getSelectedItem();
		return year+"-"+month+"-"+day;
	}
	
	/**
	 * 设置年月日
	 * @param date
	 */
	public void setText(String date){
		String ss[]=date.split("-");
		int year=Integer.parseInt(ss[0]);
		int month=Integer.parseInt(ss[1]);
		int day=Integer.parseInt(ss[2]);
		
		yearBox.setSelectedItem(year);
		monthBox.setSelectedItem(month);
		dayBox.setSelectedItem(day);
	}
}
