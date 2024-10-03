package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D.Double;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import BUS.SanPhamBUS;
import BUS.SizeBUS;
import BUS.chitietsanpham_BUS;
import DTO.SanPhamDTO;
import DTO.SizeDTO;
import DTO.chitietphieunhap_DTO;
import DTO.chitietsanpham_DTO;
import java.sql.SQLException;

public class panel_con_nhapsanpham extends JPanel implements MouseListener {

    private JTextField jt[];
    private String[] comment;
    private JLabel jlx, jlthanh_tien;
    private panel_them_phieunhap panel_them_phieunhap;
    private double thanhtien, gianhap;
    private int soluong;
    private boolean kiem_tra;
    private JComboBox<String> option_masp, option_masize;
    private SanPhamDTO SanPhamDTO;
    private SanPhamBUS sanPhamBUS;
    private chitietsanpham_BUS chitietsanpham_BUS;
    private ArrayList<panel_con_nhapsanpham> ds;
    private boolean isNewPanel;
    private int oldSoluong = 0;
    private double oldGianhap = 0;
private boolean isUpdated = false;  // Biến cờ để kiểm soát

    public panel_con_nhapsanpham(int w, int m, panel_them_phieunhap panel_them_phieunhap) {
        this.thanhtien = 0;
        this.soluong = 0;
        gianhap = 0;
        this.kiem_tra = false;
        this.ds = panel_them_phieunhap.ds_hang_them();
        ds.add(this);
        this.panel_them_phieunhap = panel_them_phieunhap;
        this.isNewPanel = true;

        this.setPreferredSize(new Dimension(w, 60));
        this.setLayout(new BorderLayout());
        comment = new String[]{"MASP", "MA SIZE", "Số lượng", "Giá nhập", "Thành tiền"};
        Font x = new Font(null, 0, 10);

        jt = new JTextField[5];
        JPanel center = new JPanel();
        center.setPreferredSize(new Dimension(550, 30));
        center.setLayout(new FlowLayout(0, 10, 0));

        sanPhamBUS = new SanPhamBUS();
        SizeBUS sizeBUS = new SizeBUS();
        ArrayList<String> list_masp = new ArrayList<String>();
        ArrayList<String> list_maSize = new ArrayList<String>();
        list_masp.add("");
        list_maSize.add("");
        for (SanPhamDTO h : sanPhamBUS.getDsSP()) {
            list_masp.add(h.getMaSP());
        }
        for (SizeDTO h : sizeBUS.getList()) {
            list_maSize.add(h.getMASIZE());
        }

        DecimalFormat format_double = new DecimalFormat("#,###");

        format_double.format(thanhtien);

        /////////////////////////////	 MASP 	/////////////////////////////
        option_masp = new JComboBox<String>(list_masp.toArray(new String[list_masp.size()]));
        option_masp.setPreferredSize(new Dimension(99, 20));

        ///////////////////////////     MA SIZE  	 ////////////////////////////
        option_masize = new JComboBox<String>(list_maSize.toArray(new String[list_maSize.size()]));
        option_masize.setPreferredSize(new Dimension(99, 20));

        option_masp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kiem_tra_trung_sanpham(); // Kiểm tra trùng khi mã sản phẩm thay đổi
            }
        });

        option_masize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kiem_tra_trung_sanpham(); // Kiểm tra trùng khi mã size thay đổi
            }
        });

        //////////////////// 	SỐ LƯỢNG 	/////////////////////////////
        jt[2] = new JTextField(comment[2]);
        jt[2].setPreferredSize(new Dimension(99, 20));
        jt[2].setForeground(Color.GRAY);
        jt[2].addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                if (jt[2].getText().equals("")) {
                    jt[2].setText(comment[2]);
                    jt[2].setForeground(Color.GRAY);
                }
                // Kiểm tra nếu giá trị mới khác với giá trị cũ
                try {
                    int newSoluong = Integer.parseInt(jt[2].getText());
                    // Cập nhật lại border thành màu xám khi giá trị hợp lệ
                    jt[2].setBorder(BorderFactory.createLineBorder(Color.GRAY));

                    if (newSoluong != oldSoluong) {
                        soluong = newSoluong;
                        thanhtien = soluong * gianhap;

                        // Cập nhật thành tiền
                        String l = format_double.format(thanhtien);
                        jlthanh_tien.setText("Thành tiền : " + l);
                        kiem_tra = true;

                        // Cộng tổng tiền
                        panel_con_nhapsanpham.this.panel_them_phieunhap.tong_tien_cong(thanhtien);

                        // Cập nhật giá trị cũ
                        oldSoluong = newSoluong;
                    }
                } catch (Exception e2) {
                    jt[2].setBorder(BorderFactory.createLineBorder(Color.red));  // Hiển thị viền đỏ nếu lỗi
                    jt[2].setOpaque(true);
                    kiem_tra = false;
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (jt[2].getText().equals(comment[2])) {
                    jt[2].setText("");
                    jt[2].setForeground(Color.black);
                }
            }
        });

        jt[2].addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.') {
                    e.consume();
                }
            }
        });

        ////////////////////////	 Giá nhập 	/////////////////////////
        jt[3] = new JTextField(comment[3] + "/vnd");
        jt[3].setPreferredSize(new Dimension(99, 20));
        jt[3].setForeground(Color.GRAY);
        jt[3].addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                if (jt[3].getText().equals("")) {
                    jt[3].setText(comment[3] + "/vnd");
                    jt[3].setForeground(Color.GRAY);
                }
                // Kiểm tra nếu giá trị mới khác với giá trị cũ
                try {
                    // Lọc các ký tự không hợp lệ, chỉ giữ lại số và dấu chấm
                    String gianhapStr = jt[3].getText().trim();
                    gianhapStr = gianhapStr.replace(",", ".");
                    // Chuyển đổi chuỗi sau khi lọc thành số
                    double newGianhap = java.lang.Double.parseDouble(jt[3].getText());
                    System.err.println("giá nhập mới" + newGianhap);
                    if (newGianhap != oldGianhap) {
                        gianhap = newGianhap;
                        thanhtien = soluong * gianhap;

                        // Cập nhật thành tiền
                        String l = format_double.format(thanhtien);
                        jlthanh_tien.setText("Thành tiền : " + l+" Đ");
                        jt[3].setBorder(BorderFactory.createLineBorder(Color.GRAY));
                        kiem_tra = true;

                        // Cộng tổng tiền
                        panel_con_nhapsanpham.this.panel_them_phieunhap.tong_tien_cong(thanhtien);

                        // Cập nhật giá trị cũ
                        oldGianhap = newGianhap;
                    }
                } catch (Exception e2) {
                    jt[3].setBorder(BorderFactory.createLineBorder(Color.red));
                    kiem_tra = false;
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (jt[3].getText().equals(comment[3] + "/vnd")) {
                    jt[3].setText("");
                    jt[3].setForeground(Color.black);
                }
            }
        });

        jt[3].addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.') {
                    e.consume();
                }
            }
        });

        /////////////////////////	 Thành tiền		 /////////////////////////////////
        jlthanh_tien = new JLabel("Thành tiền : " + thanhtien +" Đ", JLabel.CENTER);
        jlthanh_tien.setPreferredSize(new Dimension(200, 20));

        ///////////////////////////// dau xoa panel ///////////////////////////////////
        JPanel jpr = new JPanel();
        jpr.setLayout(new FlowLayout(2, 0, 0));
        jlx = new JLabel("X", JLabel.CENTER);
        jlx.setPreferredSize(new Dimension(10, 10));
        jlx.setFont(x);
        jlx.addMouseListener(this);

        jpr.add(jlx);

        jpr.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        center.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        center.add(option_masp);
        center.add(jt[2]);
        center.add(jt[3]);
        center.add(jlthanh_tien);
        center.add(option_masize);

        JLabel title = new JLabel("Sản phẩm " + m);

        JPanel panel_north = new JPanel();
        panel_north.setLayout(new GridLayout(1, 2));

        panel_north.add(title);
        panel_north.add(jpr);

        this.add(panel_north, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);

        this.setBorder(BorderFactory.createLineBorder(Color.black));

        this.addMouseListener(this);
    }

    public boolean kiem_tra_trung_sanpham() {
        String selectedMaSP = (String) option_masp.getSelectedItem();
        String selectedMaSize = (String) option_masize.getSelectedItem();

        // Kiểm tra nếu mã sản phẩm và mã size chưa được chọn
        if (selectedMaSP == null || selectedMaSP.isEmpty() || selectedMaSize == null || selectedMaSize.isEmpty()) {
            return false; // Không cần kiểm tra khi chưa chọn
        }

        // Kiểm tra trùng lặp với các sản phẩm đã thêm vào ds
        for (panel_con_nhapsanpham panel : ds) {
            if (panel != this) { // Bỏ qua chính panel hiện tại
                chitietphieunhap_DTO ctpn = panel.return_chitietphieunhap();
                if (ctpn.getMasp().equals(selectedMaSP) && ctpn.getMasize().equals(selectedMaSize)) {
                    // Nếu trùng, hiện thông báo lỗi
                    JOptionPane.showMessageDialog(this, "Mã sản phẩm và mã size đã tồn tại!", "Lỗi trùng sản phẩm", JOptionPane.ERROR_MESSAGE);

                    // Reset lại JComboBox hoặc thực hiện hành động cần thiết
                    option_masp.setSelectedIndex(0); // Đặt lại mã sản phẩm
                    option_masize.setSelectedIndex(0); // Đặt lại mã size
                    return true; // Đã phát hiện trùng
                }
            }
        }
        return false; // Không trùng
    }

    public boolean kiem_tra() {
        return this.kiem_tra;
    }

    public double thanhtien() {
        return this.thanhtien;
    }

    public double gianhap() {
        return this.gianhap;
    }

    public SanPhamDTO return_sanpham() {
        SanPhamDTO h = new SanPhamDTO();
        String masp = (String) option_masp.getSelectedItem();
        for (SanPhamDTO k : sanPhamBUS.getDsSP()) {
            if (k.getMaSP().equals(masp)) {
                h = k;
            }
        }

        double price = gianhap + gianhap * this.panel_them_phieunhap.loi_nhuan() / 100;
        double roundedPrice = Math.ceil(price / 1000) * 1000;
        h.setPrice(roundedPrice);
        h.setTrangThai(1);
        return h;

    }

    public chitietphieunhap_DTO return_chitietphieunhap() {
        String mapn = this.panel_them_phieunhap.get_newMAPN();
        String masp = (String) option_masp.getSelectedItem();
        String masize = (String) option_masize.getSelectedItem();

        chitietphieunhap_DTO c = new chitietphieunhap_DTO(mapn, masp, soluong, gianhap, thanhtien, masize);
        return c;

    }

    public boolean kiem_tra_rong() {
        String masp = (String) option_masp.getSelectedItem();
        String masize = (String) option_masize.getSelectedItem();
        String soluong = jt[2].getText().trim();
        String gianhap = jt[3].getText().trim();

        if (masp.isEmpty() || soluong.isEmpty() || gianhap.isEmpty() || masize.isEmpty()) {
            return true;

        }

        return false;
    }

    public void xacnhan() {
        gianhap = java.lang.Double.parseDouble(jt[3].getText());
        soluong = Integer.parseInt(jt[2].getText());
        thanhtien = gianhap * soluong;
        jlthanh_tien.setText(thanhtien + "");

    }

    public void update_price() {
        this.sanPhamBUS.set(this.return_sanpham());
    }

    public boolean giamoi_caohon_giacu() {
        SanPhamBUS b = new SanPhamBUS();
        String masp = (String) option_masp.getSelectedItem();

        if (this.return_sanpham().getPrice() < b.select_by_id(masp).getPrice()) {

            return false;
        }
        return true;
    }

    public void update_chitietsanpham() throws SQLException {
    // Kiểm tra nếu đã chạy rồi thì không thực hiện nữa
    if (isUpdated) {
        return;  // Không làm gì nếu đã cập nhật trước đó
    }

    // Nếu chưa chạy lần nào thì tiếp tục thực hiện
    xacnhan();
    String masp = (String) option_masp.getSelectedItem();
    String masize = (String) option_masize.getSelectedItem();

    chitietsanpham_BUS = new chitietsanpham_BUS();
    if (!chitietsanpham_BUS.select_masize_by_MASP(this.return_sanpham()).contains(masize)) {
        chitietsanpham_BUS.add(new chitietsanpham_DTO(masp, masize, soluong));
    } else {
        chitietsanpham_BUS.updateAfterTT_by_tiep(new chitietsanpham_DTO(masp, masize, soluong));
        System.err.println("fffffffffffffffff");
    }

    // Đánh dấu là đã cập nhật để không chạy lần nữa
    isUpdated = true;
}
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == jlx) {
            this.removeAll();

            this.setPreferredSize(new Dimension(0100, 0));
            this.repaint();
            this.revalidate();
            this.panel_them_phieunhap.tong_tien_tru(thanhtien);
            this.panel_them_phieunhap.ds_hang_them().remove(this);
        }

        if (!kiem_tra_rong()) {
//			System.out.println(this.chenh_lech_price());

            System.out.println("khongg rong");
        } else {
            System.out.println("rong");
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == jlx) {
            jlx.setBackground(Color.red);
            jlx.setOpaque(true);
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == jlx) {
            jlx.setBackground(null);
            jlx.setOpaque(true);
        }

    }
}
