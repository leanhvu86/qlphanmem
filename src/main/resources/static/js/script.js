$(function () {
    var currencies = [
        { value: 'Ga Hà Nội', data: 'ghn' },
        { value: 'Yên Viên', data: 'yv' },
        { value: 'Đại học CN Nhổn', data: 'dhcnn' },
        { value: 'Nội Bài', data: 'nb' },
        { value: 'Hà Đông', data: 'hd' },
        { value: 'Hồ Tây - Thanh Niên', data: 'htn' },
        { value: 'Khách sạn Deawoo - Kim Mã', data: 'kmdw' },
        { value: 'Giải Phóng', data: 'GP' },
        { value: 'Cát Linh', data: 'cl' },
        { value: 'Âu Cơ - chợ hoa Quảng Bá', data: 'acqb' },
        { value: 'Đại lộ Thăng Long', data: 'dltl' },
        { value: 'Phan Đình Phùng', data: 'pd' },
        { value: 'Giải Phóng', data: 'GP' },
        { value: 'Hồ Gươm', data: 'hg' },
        { value: 'Bến xe Kim Mã', data: 'bxkm' },
        { value: 'Khách sạn Cầu Giấy', data: 'kscg' },
        { value: 'Đại học sư phạm Hà Nội', data: 'dhsP' },
        { value: 'Nghĩa trang Mai Dịch', data: 'ntmd' },
        { value: 'Văn Tiến Dũng', data: 'vtd' },
        { value: 'Hoàng Cầu', data: 'hc' },
        { value: 'Thái Thịnh', data: 'tt' },
        { value: 'Chính Kinh', data: 'ck' },
        { value: 'Đại học Hà Nội', data: 'dhhn' },
        { value: 'Hồ Gươm Plaza', data: 'hgp' },
        { value: 'Bưu điện Hà Đông', data: 'bdhd' },
        { value: 'Khu đô thị Văn Phú', data: 'vphd' },
        { value: 'Bến xe Yên Nghĩa', data: 'bxyn' },
        { value: 'Trần Hưng Đạo', data: 'thd' },
        { value: 'Trần Quang Khải', data: 'tqk' },
        { value: 'Bến xe Long Biên', data: 'bxlb' },
        { value: 'Ngô Gia Tự', data: 'ngt' },
        { value: 'Cầu Đuống', data: 'cd' },
        { value: 'Thị trấn Yên Viên', data: 'ttyv' },
        { value: 'Ninh Hiệp', data: 'nh' },
        { value: 'Bộ Công An Phạm Văn Đồng', data: 'bcapvd' },
        { value: 'Khu đô thị Ciputra(PVD)', data: 'cpvd' },
        { value: 'Mê Linh Plaza', data: 'mlp' },
        { value: 'Sân bay Nội bài', data: 'sbnb' },
        { value: 'Khu đô thị Sa La', data: 'kdtsl' },
        { value: 'Tả Thanh Oai', data: 'to' },
        { value: 'Nghĩa trang Văn Điển', data: 'ntvd' },
        { value: 'Thị trấn Văn Điển', data: 'ttvd' },
        { value: 'Bệnh viện nội tiết TW', data: 'bvbtw' },
        { value: 'Bến xe Nước Ngầm', data: 'bxn' },
        { value: 'Bệnh viện Bạch Mai', data: 'bvm' },
        { value: 'Ga Giáp Bát', data: 'gb' },
        { value: 'Khu đô thị Gamuda', data: 'kdtg' },
        { value: 'Chợ đầu mối Lĩnh Nam', data: 'cdmln' },
        { value: 'Time city', data: 'tc' },
        { value: 'Nguyễn Khoái', data: 'nk' },
        { value: 'Bưởi', data: 'b' },
        { value: 'Trung Yên Plaza', data: 'typ' },
        { value: 'Atermis - Lê Trọng Tấn', data: 'atl' },
        { value: 'Khu đô thị Ciputra(VCC)', data: 'kdtvc' },

        { value: 'Trung tâm hội nghị Quốc Gia', data: 'thnqg' },
        { value: 'Thiên đường Bảo Sơn', data: 'tdbs' },
        { value: 'Làng văn hóa các dân tộc', data: 'cvhtl' },
        { value: 'Khu công nghệ cao Hòa Lạc', data: 'kcnhl' },
    ];
    $('#autocomplete').autocomplete({
        lookup: currencies,
        onSelect: function (suggestion) {
            var thehtml = '<strong>Currency Name:</strong> ' + suggestion.value + ' <br> <strong>Symbol:</strong> ' + suggestion.data;
            $('#outputcontent').html(thehtml);
        }
    });
    $('#autocomplete1').autocomplete({
        lookup: currencies,
        onSelect: function (suggestion) {
            var thehtml = '<strong>Currency Name:</strong> ' + suggestion.value + ' <br> <strong>Symbol:</strong> ' + suggestion.data;
            $('#outputcontent').html(thehtml);
        }
    });
});