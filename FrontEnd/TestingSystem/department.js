var accounts = []; // mảng chứa account
var v_idUpdate = -1;
var baseUrl = "http://localhost:8080/api/v1/accounts";
var baseUrlDepartment = "http://localhost:8080/api/v1/departments";

var vTheme = "";

loadData();
loadDepartment();

// load màu nên ở localStorage
vTheme = localStorage.getItem("theme");
changeTheme(vTheme);

// document.getElementById('modal-id').addEventListener('hidden.bs.modal', function name() {

// })

function changeTheme(themeValue) {
  if (themeValue === "dark") {
    // thêm class .dark-theme vào body
    $("body").addClass("dark-theme");
  } else {
    $("body").removeClass("dark-theme");
  }
  localStorage.setItem("theme", themeValue);
}

function loadData() {
  // call api đến mockapi.io đe lấy ds account
  // jqAjax
  $.ajax({
    type: "GET",
    url: baseUrlDepartment,
    // data: "data",  -- phục cho thêm hoặc update
    dataType: "JSON",
    success: function (response) {
      // call api thanh cong
      departments = response;
      var tableContent = "";
      for (let i = 0; i < departments.length; i++) {
        tableContent += "<tr>";
        tableContent += "<td>" + departments[i].id + "</td>";
        tableContent += "<td>" + departments[i].departmentName + "</td>";
        tableContent +=
          "<td><button onclick='onHandleEdit(" +
          departments[i].id +
          ")'>Edit</button> " +
          " <button onclick='onDelete(" +
          departments[i].id +
          ")'>Delete</button></td>";
        tableContent += "</tr>";
      }
      // trước khi show data thì clear bảng trước
      //jqEmpty
      $("#tableBoby").empty();
      // jqAppend
      $("#tableBoby").append(tableContent);
    },
    error: function (error) {
      alert("Call api get department thất bại");
    },
  });
}

function onDelete(idDelete) {
  var check = confirm("Bạn có chắc chắn xóa account này?");
  if (check) {
    // dung ajax để call API xóa
    $.ajax({
      type: "DELETE",
      url: baseUrlDepartment + "/" + idDelete,
      // data: "data",
      // dataType: "dataType", dung cho GET
      success: function (response) {
        alert("Xóa thành công!");
        loadData();
      },
      error: function (error) {
        alert("Call api xóa thất bại");
      },
    });
  }
}

function onCreate(idDelete) {
  if (v_idUpdate > 0) {
    alert("Đang update, ko thể tạo mới dc");
    return;
  }
  var v_name = $("#inputDepartmentName").val();

  // đưa các dữ liệu trên vào object // object của js
  var department = {
    name: v_name,
  };
  //https://images2.thanhnien.vn/528068263637045248/2024/1/25/e093e9cfc9027d6a142358d24d2ee350-65a11ac2af785880-17061562929701875684912.jpg
  // call api dể thêm mới account
  $.ajax({
    type: "POST",
    url: baseUrlDepartment,
    data: JSON.stringify(department), // chuyển account từ obejct của JS thành JSON
    contentType: "application/json",
    success: function (response) {
      alert("Thêm dữ liệu thành công");
      // hiển thị lại ds account
      loadData();
      // clear dữ lieu 3 ô username, fullName, age ở tren
      //jqValSet
      $("#inputDepartmentName").val("");
      $("#modal-id").modal("hide");
    },
    error: function (error) {
      alert("Call api thêm mới thất bại");
    },
  });
}

// jqSubmit
// $("#accountForm").submit(function (e) {
//     e.preventDefault();

// });

$("#submit").click(function (e) {
  // nếu v_idUpdate <= 0    thì sẽ tạo mới
  // nếu v_idUpdate > 0 thì sẽ update
  if (v_idUpdate <= 0) {
    onCreate();
  } else {
    onUpdate();
  }
});

function resetForm() {
  $(".modal-title").empty();
  $(".modal-title").append("<div>Create Account</div>");
  $("#inputAvatar").val("");
  $("#inputUsername").val("");
  $("#inputFullname").val("");
  $("#inputAge").val("");
  v_idUpdate = -1;
}

function onHandleEdit(idUpdate) {
  // mo modal
  $(".modal-title").text("Update Department");
  // call api get by id đẻ lấy lấy dữ liệu ra để hiển thị lên các ô input
  $.ajax({
    type: "GET",
    url: baseUrlDepartment + "/" + idUpdate,
    // data: "data",
    dataType: "JSON",
    success: function (response) {
      $(".modal-title").empty();
      $(".modal-title").append("<div>Update Account</div>");
      // hien thi ra cac o input tuong ung

      // cho các ô select chọn đúng gtri của phòng ban và chức vụ
      $("#inputDepartmentName").val(response.departmentName);

      v_idUpdate = idUpdate; // lưu lại id cần update
    },
    error: function (error) {
      alert("Call api lấy thông tin thất bại");
    },
  });
}

function loadDepartment() {
  $.ajax({
    type: "GET",
    url: baseUrlDepartment,
    dataType: "JSON",
    success: function (response) {
      var content = "";
      for (let i = 0; i < response.length; i++) {
        content += `<option value="${response[i].id}">${response[i].departmentName}</option>`;
      }
      $("#inputDepartmentName").empty();
      $("#inputDepartmentName").append(content);
    },
    error: function (error) {
      alert("Call api get department that bai");
    },
  });
}

function onUpdate() {
  var v_departmentName = $("#inputDepartmentName").val();

  var departmentUpdate = {
    name: v_departmentName,
  };

  $.ajax({
    type: "PUT",
    url: baseUrlDepartment + "/" + v_idUpdate,
    data: JSON.stringify(departmentUpdate),
    contentType: "application/json",

    success: function (response) {
      alert("Update dữ liệu thành công");

      loadData();

      v_idUpdate = -1;

      $("#inputDepartmentName").val("");

      $("#modal-id").modal("hide");
    },

    error: function (error) {
      alert("Call api update thất bại");
    },
  });
}
