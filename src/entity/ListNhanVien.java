package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class ListNhanVien implements Serializable{
	private ArrayList<NhanVien> listNV;
	public ListNhanVien() {
		// TODO Auto-generated constructor stub
		listNV = new ArrayList<NhanVien>();
	}
	public boolean them(NhanVien nv) {
		if(listNV.contains(nv))
			return false;
		return listNV.add(nv);
	}
	public ArrayList<NhanVien> getListNV() {
		return listNV;
	}
	public boolean xoa(NhanVien nv) {
		return listNV.remove(nv);
	}
	
}
