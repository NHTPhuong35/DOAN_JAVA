package GUI;

import BUS.ChitietHD_BUS;
import BUS.SanPhamBUS;
import BUS.chitietphieunhap_BUS;
import BUS.chitietsanpham_BUS;
import BUS.loaiSPBUS;
import DTO.SanPhamDTO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;

public class SanPhamGUI extends JPanel implements MouseListener {

    private SanPhamBUS spBUS = new SanPhamBUS();
    private ArrayList<SanPhamDTO> dsSP;
    SanPhamDTO selectedSP = new SanPhamDTO();
    private JLabel lblChiTietSP;

    private JPanel[] product;
    private int chieurong, chieucao;
    private Color backGroundColor;
    private Color normal = Color.decode("#0A3D62");
    private Font font_family = new Font("Tahoma", Font.BOLD, 12);

    private DecimalFormat FormatInt = new DecimalFormat("#,###");

    public SanPhamGUI(int chieurong, int chieucao, Color backG_thisJPanel) {
        this.chieurong = chieurong;
        this.chieucao = chieucao;
        this.backGroundColor = backG_thisJPanel;

        // Tối ưu hóa việc lấy dữ liệu
        loaiSPBUS lBUS = new loaiSPBUS();
        dsSP = spBUS.getFilteredProducts(lBUS); // Đơn giản hóa logic lọc dữ liệu thành một phương thức
        init();
    }

    public void init() {
        this.setPreferredSize(new Dimension(chieurong, chieucao));
        this.setLayout(new BorderLayout());
        this.setBackground(backGroundColor);
        this.setOpaque(true);

        JPanel mainPanel = initContent(dsSP);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    public JPanel initContent(ArrayList<SanPhamDTO> dsSP) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 30));
        panel.setBackground(backGroundColor);
        panel.setOpaque(true);

        // Tính toán kích thước dựa trên số lượng sản phẩm
        int dsSize = dsSP.size();
        int rows = (int) Math.ceil((double) dsSize / 6);

        if (dsSize <= 6) {
            panel.setPreferredSize(new Dimension(chieurong - 5, 330));
        } else if (dsSize > 6) {
            panel.setPreferredSize(new Dimension(chieurong - 5, 330 * rows));
        }

        product = new JPanel[dsSize];
        for (int i = 0; i < dsSize; i++) {
            product[i] = createProductPanel(dsSP.get(i));
            panel.add(product[i]);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createProductPanel(SanPhamDTO sp) {
        JPanel productPanel = new JPanel();
        productPanel.setPreferredSize(new Dimension(180, 300));
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
        productPanel.setAlignmentY(TOP_ALIGNMENT);
        productPanel.setBackground(Color.WHITE); // Đặt màu nền trắng hoặc tùy ý

        // Hình ảnh sản phẩm
        ImageIcon icon = new ImageIcon("./src/images/" + (sp.getTenHinh().length > 0 ? sp.getTenHinh()[0] : "t-shirt.png"));
        Image scaledImage = icon.getImage().getScaledInstance(180, 210, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(scaledImage), JLabel.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều ngang

        // Tên sản phẩm
        JLabel productName = new JLabel(sp.getTenSP(), JLabel.CENTER);
        productName.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều ngang

        // Giá sản phẩm
        JLabel productPrice = new JLabel("Giá: " + FormatInt.format(sp.getPrice()) + " đồng", JLabel.CENTER);
        productPrice.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều ngang

        // Nhãn xem chi tiết
        lblChiTietSP = createChiTietLabel(sp);
        lblChiTietSP.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều ngang

        // Thêm các thành phần vào productPanel
        productPanel.add(label);
        productPanel.add(Box.createVerticalStrut(5)); // Khoảng cách giữa các thành phần
        productPanel.add(productName);
        productPanel.add(Box.createVerticalStrut(5));
        productPanel.add(productPrice);
        productPanel.add(Box.createVerticalStrut(5));
        productPanel.add(lblChiTietSP);

        // Cài đặt con trỏ chuột
        productPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        productPanel.addMouseListener(this);

        return productPanel;
    }

    private JLabel createChiTietLabel(SanPhamDTO sp) {
        JLabel chiTietLabel = new JLabel("Xem chi tiết", JLabel.CENTER);
        chiTietLabel.setMinimumSize(new Dimension(100, 30));
        chiTietLabel.setPreferredSize(new Dimension(100, 30));
        chiTietLabel.setMaximumSize(new Dimension(100, 30));
        chiTietLabel.setBackground(normal);
        chiTietLabel.setOpaque(true);
        chiTietLabel.setFont(font_family);
        chiTietLabel.setForeground(Color.WHITE);
        chiTietLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        chiTietLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều ngang
        chiTietLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

        chiTietLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    new frame_chitietsanpham(sp);
                } catch (SQLException ex) {
                    Logger.getLogger(SanPhamGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return chiTietLabel;
    }

    public void refresh() {
        this.removeAll();
        dsSP = spBUS.getFilteredProducts(new loaiSPBUS()); // Tối ưu hóa việc lấy dữ liệu
        JPanel mainPanel = initContent(dsSP);
        this.add(mainPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    public void EditSP(SanPhamDTO sp) {
        spBUS.set(sp);
        refresh();
        this.revalidate(); // Cập nhật lại giao diện
        this.repaint(); // Vẽ lại giao diện
    }

    public void AddSP(SanPhamDTO sp) {
        spBUS.add(sp);
        refresh();
        this.revalidate(); // Cập nhật lại giao diện
        this.repaint(); // Vẽ lại giao diện
    }

    public void DeleteSP() {
        ChitietHD_BUS cthd = new ChitietHD_BUS();
        chitietphieunhap_BUS ctpn = new chitietphieunhap_BUS();
        chitietsanpham_BUS ctsp;
        if (selectedSP == null || selectedSP.getMaSP() == null) {
            System.out.println("Mã sản phẩm không hợp lệ");
            return;
        }
        try {
            ctsp = new chitietsanpham_BUS();
            int sum = 0;
            for (int i = 0; i < ctsp.getlistByFilter(selectedSP.getMaSP()).size(); i++) {
                sum += ctsp.getlistByFilter(selectedSP.getMaSP()).get(i).getSoluong();
            }
            if (sum > 0) {
                JOptionPane.showMessageDialog(null,
                        "Số lượng sản phẩm còn không thể xoá!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            } else {
                Object[] options = {"Có", "Không"};
                int result = JOptionPane.showOptionDialog(
                        null,
                        "Bạn có chắc chắn muốn xoá sản phẩm này?", // Nội dung thông báo
                        "Xác nhận xoá", // Tiêu đề
                        JOptionPane.YES_NO_OPTION, // Tùy chọn Yes/No
                        JOptionPane.QUESTION_MESSAGE, // Biểu tượng dấu hỏi
                        null,
                        options,
                        options[0]
                );

                // Xử lý kết quả
                if (result == JOptionPane.YES_OPTION) {
                    // Kiểm tra danh sách phiếu nhập
                    for (int i = 0; i < ctpn.getList().size(); i++) {
                        if (selectedSP.getMaSP().equals(ctpn.getList().get(i).getMasp())) {
                            spBUS.delete(selectedSP.getMaSP(), true);
                            selectedSP = new SanPhamDTO();
                            refresh();
                            return; // Đã tìm thấy và xóa, thoát khỏi phương thức
                        }
                    }

                    // Kiểm tra danh sách hóa đơn chi tiết
                    for (int i = 0; i < cthd.getList().size(); i++) {
                        if (selectedSP.getMaSP().equals(cthd.list.get(i).getMaSP())) {
                            spBUS.delete(selectedSP.getMaSP(), true);
                            selectedSP = new SanPhamDTO();
                            refresh();
                            return; // Đã tìm thấy và xóa, thoát khỏi phương thức
                        }
                    }

                    // Xóa nếu không có trong các danh sách trên
                    spBUS.delete(selectedSP.getMaSP(), false);

                    // Làm mới đối tượng và cập nhật giao diện
                    selectedSP = new SanPhamDTO();
                    refresh();
                    
                    JOptionPane.showMessageDialog(null,
                            "Bạn đã xoá sản phẩm thành công!", "Thông báo", JOptionPane.DEFAULT_OPTION);
                    return;
                } else {
                    selectedSP = new SanPhamDTO();
                    clearBordersExcept(-1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SearchSP(ArrayList<String> sp) {
        loaiSPBUS loaiBUS = new loaiSPBUS();
        String maLoai = sp.get(1);
        for (int i = 0; i < loaiBUS.getList().size(); i++) {
            if (loaiBUS.getList().get(i).getTENLOAI().equals(maLoai)) {
                maLoai = loaiBUS.getList().get(i).getMALOAI();
                break;
            }
        }
        String timkiem = sp.get(0);
        ArrayList<SanPhamDTO> dsNew = new ArrayList<>();
        if (timkiem.isEmpty()) {
            if (!maLoai.equals("Tất cả")) {
                for (int i = 0; i < dsSP.size(); i++) {
                    if (dsSP.get(i).getMaLoai().equals(maLoai)) {
                        dsNew.add(dsSP.get(i));
                    }
                }
            } else if (maLoai.equals("Tất cả")) {
                dsNew = dsSP;
            }
        } else {
            ArrayList<SanPhamDTO> ds = new ArrayList<>();
            for (int i = 0; i < dsSP.size(); i++) {
                String maSP = dsSP.get(i).getMaSP().toLowerCase();
                String tenSP = dsSP.get(i).getTenSP().toLowerCase();
                timkiem = timkiem.toLowerCase();
                if (maSP.contains(timkiem) || tenSP.contains(timkiem)) {
                    ds.add(dsSP.get(i));
                }
            }
            if (!maLoai.equals("Tất cả")) {
                for (int i = 0; i < ds.size(); i++) {
                    if (ds.get(i).getMaLoai().equals(maLoai)) {
                        dsNew.add(ds.get(i));
                    }
                }
            } else {
                dsNew = ds;
            }
        }
        this.removeAll(); // Xóa tất cả các thành phần hiện tại
        JPanel mainPanel = initContent(dsNew); // Tạo lại JPanel chính
        this.add(mainPanel, BorderLayout.CENTER); // Thêm lại JPanel chính
        this.revalidate(); // Cập nhật lại giao diện
        this.repaint(); // Vẽ lại giao diện
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JPanel) {
            JPanel pn = (JPanel) e.getSource();
            for (int i = 0; i < dsSP.size(); i++) {
                if (pn == product[i]) {
                    selectedSP = dsSP.get(i);
                    pn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));
                    clearBordersExcept(i);
                }
            }
        }
    }

    void clearBordersExcept(int selectedIndex) {
        for (int i = 0; i < product.length; i++) {
            if (i != selectedIndex) {
                product[i].setBorder(null);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
