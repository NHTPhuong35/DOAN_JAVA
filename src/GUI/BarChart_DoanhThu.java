package GUI;

import DTO.ThongKeDTO;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart_DoanhThu extends JPanel {

    private ArrayList<ThongKeDTO> thk;
    private String[] mangDT = new String[30];

    public BarChart_DoanhThu(ArrayList<ThongKeDTO> thk, ArrayList<String> currentday, String tenBieuDo, String donvi) {
        this.thk = thk;
        JFreeChart barChart;
        
        // Kiểm tra tên biểu đồ và tạo biểu đồ tương ứng
        if (tenBieuDo.equals("Thống kê doanh thu")) {
            barChart = ChartFactory.createBarChart(tenBieuDo,
                    currentday.get(0) + " - " + currentday.get(1),
                    donvi,
                    createDataset(),
                    PlotOrientation.VERTICAL,
                    true, true, false);
        } else {
            barChart = ChartFactory.createBarChart(tenBieuDo,
                    currentday.get(0) + " - " + currentday.get(1),
                    donvi,
                    createDatasetBanChay(),
                    PlotOrientation.VERTICAL,
                    true, true, false);
        }

        // Tạo ChartPanel và thêm sự kiện chuột
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.addChartMouseListener(new ChartMouseListener() {
            @Override
            public void chartMouseClicked(ChartMouseEvent event) {
                // Kiểm tra nếu thực thể được nhấp là một mục trong biểu đồ
                if (event.getEntity() instanceof CategoryItemEntity) {
                    CategoryItemEntity entity = (CategoryItemEntity) event.getEntity();
                    String tenSanPham = (String) entity.getRowKey(); // Lấy tên sản phẩm
                    Number giaTri = entity.getDataset().getValue(entity.getRowKey(), entity.getColumnKey()); // Lấy giá trị của cột
                    
                    // Hiển thị thông tin chi tiết khi nhấp vào cột
                    JOptionPane.showMessageDialog(null, "Sản phẩm: " + tenSanPham + "\nGiá trị: " + giaTri + " " + donvi);
                }
            }

            @Override
            public void chartMouseMoved(ChartMouseEvent event) {
                // Không cần xử lý sự kiện khi di chuyển chuột
            }
        });

        // Cài đặt layout cho panel và thêm chartPanel vào
        this.setLayout(new BorderLayout());
        this.add(chartPanel, BorderLayout.CENTER);
    }

    // Tạo dataset cho biểu đồ doanh thu
    private CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ThongKeDTO tk : thk) {
            dataset.addValue(tk.getThanhTien(), tk.getTenSP(), "");
        }
        return dataset;
    }

    // Tạo dataset cho biểu đồ sản phẩm bán chạy
    private CategoryDataset createDatasetBanChay() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ThongKeDTO tk : thk) {
            dataset.addValue(tk.getSoLuong(), tk.getTenSP(), "");
        }
        return dataset;
    }

    public static void main(String[] args) {
        // Tạo đối tượng ThongKeGUI để hiển thị biểu đồ
        ThongKeGUI tkGUI = new ThongKeGUI(800, 600);
        ArrayList<String> currentday = new ArrayList<>();
        currentday.add("2023/01/01");
        currentday.add("2024/05/16");
        currentday.add("Tất cả");
        tkGUI.ShowdoanhThu(currentday);
        
        // Tạo biểu đồ cột và hiển thị trong JFrame
        BarChart_DoanhThu chart = new BarChart_DoanhThu(tkGUI.ds, currentday, "Thống kê doanh thu", "Đồng");

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 500);
        f.add(chart);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
}
