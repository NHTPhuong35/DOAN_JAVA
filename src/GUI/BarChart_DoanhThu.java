package GUI;

import DTO.DoanhThuDTO;
import DTO.ThongKeDTO;
import java.awt.BorderLayout;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
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

    private ArrayList<DoanhThuDTO> dthu;
    private ArrayList<ThongKeDTO> thk;
    private String[] mangDT = new String[30];
    
    public BarChart_DoanhThu(Object data, ArrayList<String> currentday, String tenBieuDo, String donvi) {
    if (data instanceof ArrayList<?>) {
        ArrayList<?> list = (ArrayList<?>) data;
        if (!list.isEmpty() && list.get(0) instanceof DoanhThuDTO) {
            this.dthu = (ArrayList<DoanhThuDTO>) data;
            // Xử lý cho DoanhThuDTO
            JFreeChart barChart = ChartFactory.createBarChart(tenBieuDo,
                    currentday.get(0) + " - " + currentday.get(1),
                    donvi,
                    createDataset(),
                    PlotOrientation.VERTICAL,
                    true, true, false);
            
            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.addChartMouseListener(new ChartMouseListener() {
                @Override
                public void chartMouseClicked(ChartMouseEvent event) {
                    if (event.getEntity() instanceof CategoryItemEntity) {
                        CategoryItemEntity entity = (CategoryItemEntity) event.getEntity();
                        String ngayBan = (String) entity.getRowKey();
                        Number giaTri = entity.getDataset().getValue(entity.getRowKey(), entity.getColumnKey());
                        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
                        String formatTien = formatter.format(giaTri);

                        JOptionPane.showMessageDialog(null, "Ngày: " + ngayBan + "\nGiá trị: " + formatTien + " " + donvi);
                    }
                }

                @Override
                public void chartMouseMoved(ChartMouseEvent event) {
                }
            });

            this.setLayout(new BorderLayout());
            this.add(chartPanel, BorderLayout.CENTER);

        } else if (!list.isEmpty() && list.get(0) instanceof ThongKeDTO) {
            this.thk = (ArrayList<ThongKeDTO>) data;
            // Xử lý cho ThongKeDTO
            JFreeChart barChart = ChartFactory.createBarChart(tenBieuDo,
                    currentday.get(0) + " - " + currentday.get(1),
                    donvi,
                    createDatasetBanChay(),
                    PlotOrientation.VERTICAL,
                    true, true, false);
            
            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.addChartMouseListener(new ChartMouseListener() {
                @Override
                public void chartMouseClicked(ChartMouseEvent event) {
                    if (event.getEntity() instanceof CategoryItemEntity) {
                        CategoryItemEntity entity = (CategoryItemEntity) event.getEntity();
                        String tenSanPham = (String) entity.getRowKey();
                        Number soLuong = entity.getDataset().getValue(entity.getRowKey(), entity.getColumnKey());
                        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
                        String formatSoluong = formatter.format(soLuong);

                        JOptionPane.showMessageDialog(null, "Sản phẩm: " + tenSanPham + "\nSố lượng: " + formatSoluong + " " + donvi);
                    }
                }

                @Override
                public void chartMouseMoved(ChartMouseEvent event) {
                }
            });

            this.setLayout(new BorderLayout());
            this.add(chartPanel, BorderLayout.CENTER);
        }
    }
}

    // Tạo dataset cho biểu đồ doanh thu
    private CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (DoanhThuDTO dt : dthu) {
            dataset.addValue(dt.getTongDoanhthu(), dt.getDate(), "");
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
        currentday.add("2024/09/01");
        currentday.add("2024/10/01");
        currentday.add("Áo sơmi");
        tkGUI.ShowdoanhThu(currentday);
        
        // Tạo biểu đồ cột và hiển thị trong JFrame
        BarChart_DoanhThu chart = new BarChart_DoanhThu(tkGUI.dstk, currentday, "Thống kê doanh thu", "Đồng");

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 500);
        f.add(chart);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
}
