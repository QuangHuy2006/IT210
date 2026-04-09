Chi tiết luồng:

Input (URL): /bai4/products?category=chay&limit=10

Tham số 1: category (giá trị: "chay")

Tham số 2: limit (giá trị: 10)

Controller: Nhận dữ liệu qua @RequestParam.


ModelMap (Data Carrier):

Key: cat -> Value: category

Key: lim -> Value: limit

Key: msg -> Value: "Tìm kiếm thành công"

Output (View): Trả về file productList.jsp.

Sử dụng ${cat}, ${lim}, ${msg} để hiển thị.