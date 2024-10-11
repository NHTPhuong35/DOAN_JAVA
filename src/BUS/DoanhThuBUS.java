package BUS;

import DAO.DoanhThuDAO;
import DTO.DoanhThuDTO;
import java.util.ArrayList;

public class DoanhThuBUS {
    private ArrayList<DoanhThuDTO> dsDT;

    public DoanhThuBUS() {
    }
    
    public ArrayList<DoanhThuDTO> listDoanhThu(ArrayList<String> data_filters){
        DoanhThuDAO dthuDAO = new DoanhThuDAO();
        dsDT = new ArrayList<>();
        dsDT = dthuDAO.listDoanhThu(data_filters);
        return dsDT;
    }
}
