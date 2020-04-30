var checkadd = [false, false, false, false];
var checkdel = [false];

//校验成功函数
function success(Obj, counter, check, offset) {
    Obj.parent().parent().removeClass('has-error').addClass('has-success');
    $('.tips').eq(counter + offset).hide();
    $('.glyphicon-ok').eq(counter + offset).show();
    $('.glyphicon-remove').eq(counter + offset).hide();
    check[counter] = true;

}

// 校验失败函数
function fail(Obj, counter, msg, check, offset) {
    Obj.parent().parent().removeClass('has-success').addClass('has-error');
    $('.glyphicon-remove').eq(counter + offset).show();
    $('.glyphicon-ok').eq(counter + offset).hide();
    $('.tips').eq(counter + offset).text(msg).show();
    check[counter] = false;
}

//添加图书逻辑

//ISBN
var regISBN = /^[0-9]{13}$/;
$('#add').find('input').eq(0).change(function () {
    if (regISBN.test($(this).val())) {
        success($(this), 0, checkadd, 0);
    } else {
        fail($(this), 0, 'ISBN只能为13位数字', checkadd, 0);
    }
});
//bname
$('#add').find('input').eq(1).change(function () {
    if ($(this).val().length < 3) {
        fail($(this), 1, '失物名太短，不能少于3个字符', checkadd, 0);
    } else {
        success($(this), 1, checkadd, 0);
    }
});

//writer
$('#add').find('input').eq(2).change(function () {
    if ($(this).val().length < 1) {
        fail($(this), 2, '地址太短', checkadd, 0);
    } else {
        success($(this), 2, checkadd, 0);
    }
});
//ptime
var regtime = /^[0-9]{4}[\-]{1}[0-9]{2}[\-]{1}[0-9]{2}$/;
$('#add').find('input').eq(3).change(function () {
    if (regtime.test($(this).val())) {
        success($(this), 3, checkadd, 0);
    } else {
        fail($(this), 3, '日期应形如XXXX-XX-XX', checkadd, 0);
    }
});


//add
$('#btn-add').click(function (e) {
    if (!checkadd.every(function (value) {
        return value == true
    })) {
        e.preventDefault();
        for (key in checkadd) {
            if (!checkadd[key]) {
                $('#add').find('input').eq(key).parent().parent().removeClass('has-success').addClass('has-error')
            }
        }
    } else {
        var ISBN = document.getElementById("ad-isbn").value;
        var name = document.getElementById("ad-name").value;
        var address = document.getElementById("ad-address").value;
        var ptime = document.getElementById("ad-ptime").value;
        var lostList = {
            'lostNo': ISBN, 'lostName': name,
            'address': address,
            'createTime': ptime
        };
        var JSONdata = JSON.stringify(lostList);
        $.ajax({
            type: "post",
            url: "/lost/add.do",
            data: JSONdata,
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            success: function (result) {
                if (result.code == 200) {
                    alert(result.data);
                } else {
                    alert("添加失败");
                }
            }
        });
    }
});

//delete
var regISBN = /^[0-9]{13}$/;
$('#delete').find('input').eq(0).change(function () {
    if (regISBN.test($(this).val())) {
        success($(this), 0, checkdel, 6);
    } else {
        fail($(this), 0, 'ISBN只能为13位数字', checkdel, 6);
    }
});

$('#btn-delete').click(function (e) {
    if (!checkadd.every(function (value) {
        return value == true
    })) {
        e.preventDefault();
        for (key in checkdel) {
            if (!checkdel[key]) {
                $('#delete').find('input').eq(key).parent().parent().removeClass('has-success').addClass('has-error')
            }
        }
    } else {
        var ISBN = document.getElementById("dl-isbn").value;
        var booklist = {
            'isbn': ISBN
        };
        var JSONdata = JSON.stringify(booklist);
        $.ajax({
            type: "post",
            url: "/book/admin/delete",
            data: JSONdata,
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            success: function (result) {
                if (result['success']) {
                    alert("删除成功");
                } else {
                    alert("删除失败");
                }
            }
        });
    }
});
