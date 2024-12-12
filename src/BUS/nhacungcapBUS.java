/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

/**
 *
 * @author hp
 */
import java.util.ArrayList;
import DTO.nhacungcapDTO;
import DAO.nhacungcapDAO;
import GUI.nhacungcapGUI;
import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import javax.swing.JFileChooser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JOptionPane;

public class nhacungcapBUS {

    private ArrayList<nhacungcapDTO> listNhacungcapFull;
    private ArrayList<nhacungcapDTO> listNhacungcapRemove0;

    public nhacungcapBUS() {
        listNhacungcapFull = new ArrayList<>();
        listNhacungcapRemove0 = new ArrayList<>();
        init();
    }

    public boolean checkTENNCC(String t) {
        //tên nhà cung cấp không chứa số và các kí tự đặc biệt
        String regex = "^[\\p{L} ]+$";
        return t.matches(regex);
    }

    public boolean checkSDT(String t) {
        //tổng cộng 10 chữ số: bắt đầu là số 0
        String regex = "^0[0-9]{9}$";
        return t.matches(regex);
    }

    private void init() {
        nhacungcapDAO n = new nhacungcapDAO();
        listNhacungcapFull = n.listNhacungcap();
        listNhacungcapRemove0 = n.listNhacungcapRemoveTrangthai0();
    }

    public ArrayList<nhacungcapDTO> getList() {
        return listNhacungcapRemove0;
    }

    private String createMANCC() {
        int max = 0;
        for (int i = 0; i < listNhacungcapFull.size(); i++) {
            String MANCClast = listNhacungcapFull.get(i).getMANCC();
            String so = MANCClast.replaceAll("[^0-9]", "");
            int stt = Integer.parseInt(so) + 1;
            if (stt > max) {
                max = stt;
            }
        }
        return "NCC" + max;

    }

    public void add(nhacungcapDTO ncc) {

        ncc.setMANCC(createMANCC());
        listNhacungcapFull.add(ncc);
        listNhacungcapRemove0.add(ncc);
        nhacungcapDAO n = new nhacungcapDAO();
        n.add(ncc);
    }

    public void deleteInSQL(String maDelete) {
        nhacungcapDAO nccDAO = new nhacungcapDAO();
        nccDAO.delete(maDelete);
    }

    public void updateInSQL() {
        nhacungcapDAO nccDAO = new nhacungcapDAO();
        for (nhacungcapDTO ncc : listNhacungcapRemove0) {
            nccDAO.update(ncc);
        }
    }

    public void updateInSQL(nhacungcapDTO ncc) {
        nhacungcapDAO nccDAO = new nhacungcapDAO();
        nccDAO.update(ncc);
    }

    public void delete(String MANCC) {
        for (int i = 0; i < listNhacungcapRemove0.size(); i++) {
            if (listNhacungcapRemove0.get(i).getMANCC().equals(MANCC)) {
                listNhacungcapRemove0.remove(i);
            }
        }
    }

    public ArrayList<nhacungcapDTO> search(ArrayList<String> data_filter) {

        String value = data_filter.get(0).trim();
        String regexValue = "^[a-zA-Z 0-9]+$";
        if (value.equals("")) return listNhacungcapRemove0;
        if (!value.matches(regexValue)) {
            JOptionPane.showMessageDialog(null, "Không nhận kí tự đặc biệt");
            return listNhacungcapRemove0;
        } else {
            ArrayList<nhacungcapDTO> re = new ArrayList<>();
            for (String i : data_filter) {
                for (nhacungcapDTO j : listNhacungcapRemove0) {
                    boolean cond = true;
                    if (!value.equals("")) {
                        cond = j.getMANCC().toLowerCase().contains(i.toLowerCase()) || j.getTENNCC().toLowerCase().contains(i.toLowerCase()) || j.getSDT().contains(i);
                    }
                    if (cond) {
                        re.add(j);
                    }

                }
                return re;

            }
        }
        return null;
    }

    public boolean checkNewListNCC(ArrayList<nhacungcapDTO> newList) {
        boolean flag = true;
        for (int i = 0; i < listNhacungcapRemove0.size(); i++) {
            if (!listNhacungcapRemove0.get(i).equals(newList.get(i))) {
                if (newList.get(i).getTENNCC().equals("") || newList.get(i).getSDT().equals("")) {
                    continue;
                }
                if (checkTENNCC(newList.get(i).getTENNCC()) && checkSDT(newList.get(i).getSDT())) {
                    listNhacungcapRemove0.get(i).setTENNCC(newList.get(i).getTENNCC());
                    listNhacungcapRemove0.get(i).setSDT(newList.get(i).getSDT());
                } else {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    public boolean importExcelData(nhacungcapGUI nccGUI) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String nameFileSelected = selectedFile.getName();
            if (!nameFileSelected.endsWith(".xlsx") && !nameFileSelected.endsWith(".xls")) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn tệp có định dạng .xlsx hoặc .xls");
                return false;
            }
            if (!selectedFile.exists()) {
                JOptionPane.showMessageDialog(null, "không tìm thấy tập tin đã chọn");
                return false;
            }

            try {
                FileInputStream excelFile = new FileInputStream(selectedFile);
                Workbook workbook = new XSSFWorkbook(excelFile);
                Sheet sheet = workbook.getSheetAt(0);
                for (Row row : sheet) {

                    String ten = "";
                    String sdt = "";
                    int j = 0;
                    boolean flag = true;
                    for (Cell cell : row) {

                        switch (cell.getCellType()) {
                            case STRING: {
                                if ((j++) == 0) {
                                    ten = (cell.getStringCellValue()).trim();
                                    if (!checkTENNCC(ten)) {
                                        flag = false;
                                    } else {
                                        for (nhacungcapDTO ncc : listNhacungcapRemove0) {
                                            if (ncc.getTENNCC().equals(ten)) {
                                                flag = false;
                                            }
                                        }
                                    }

                                } else {
                                    sdt = (cell.getStringCellValue()).trim();
                                    if (!checkSDT(sdt)) {
                                        flag = false;
                                    } else {
                                        for (nhacungcapDTO ncc : listNhacungcapRemove0) {
                                            if (ncc.getSDT().equals(sdt)) {
                                                flag = false;
                                            }
                                        }
                                    }
                                }
                                break;
                            }
                            default: {
                                flag = false;
                                break;
                            }

                        }
                    }
                    if (flag) {
                        nhacungcapDTO nccDTO = new nhacungcapDTO(ten, sdt);
                        add(nccDTO);
                        nccGUI.addLineDataInTable(nccDTO);
                    }
                }

                workbook.close();
                excelFile.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (result == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "Đã thoát khỏi hộp thoại lựa chọn file excel cần nhập");
            return false;
        }
        return true;
    }

}
