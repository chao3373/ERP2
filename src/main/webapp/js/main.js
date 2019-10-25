//打开dlg按钮
function openDlg(dlg) {
    $(dlg).dialog("open");
}

//关闭dig
function closeDlg(dlg) {
    $(dlg).dialog("close");
}

//提交表单信息
function subimtFm(fm, url, dlg, dg) {
    $(fm).form('submit', {
        url: url,
        success: function (result) {
            if (result) {
                clertInput(fm);
                $(dg).datagrid("reload");
                $(dlg).dialog("close");
                alert(result);
                return;
            }
            alert("保存失败！");
            return;
        }
    });
}

//清空表单的数据
function clertInput(fm) {
    $(':input', fm)
        .not(':button, :submit, :reset, :hidden')
        .val('')
        .removeAttr('checked')
        .removeAttr('selected');
}

//将datagrid选中的行信息加载到form中,并打开所在的form所在的dlg
function dgToFm(dg, fm, dlg) {
    var rows = $(dg).datagrid("getSelections");
    if (rows.length != 1) {
        alert("请选择一条要操作的数据！");
        return;
    }
    $(fm).form("load", rows[0]);
    $(dlg).dialog("open");
}

//删除选中的信息
function deleteDg(dg, url) {
    var ids = [];
    var rows = $(dg).datagrid("getSelections");
    if (rows.length < 1) {
        alert("请选择要删除的数据！");
        return;
    }
    $.messager.confirm("系统提示", "<span style='color: red;'>确定要删除选中的信息吗！</span>", function (r) {
        if (r) {
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i].id);
            }
            var idsArr = ids.join(",");
            $.ajax({
                type: "POST",
                url: url,
                data: {ids: idsArr},
                success: function (result) {
                    if (result) {
                        $(dg).datagrid("reload");
                        alert(result);
                        return;
                    }
                    alert("删除失败！");
                    return;
                }
            })
        }
    });

}

//根据条件刷新dg中的数据
function shuaXinDg(dg, url, data) {
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        success: function (result) {
            if (result) {
                $(dg).datagrid("loadData", result);
            }
        }
    })
}

//获取from表单中的所有name和对应的值
function getFrom(from) {
    var from = $(from);
    var obj = {};
    var t = from.serializeArray();
    $.each(t, function () {
        obj[this.name] = this.value;
    });
    return obj;
}

//删除datagrid中选中的行
function deleteRows(dg) {
    var rows = $(dg).datagrid("getSelections");
    if (rows.length < 1) {
        alert("请选择要删除的行！");
        return;
    }
    for (var i = rows.length - 1; i > -1; i--) {
        console.log(i);
        var index = $(dg).datagrid("getRowIndex", rows[i]);
        console.log(index);
        $(dg).datagrid("deleteRow", index);
    }
    alert("删除成功！");
}

//格式化时间
function dateFtt(fmt, date) { //author: meizz
    var o = {
        "M+": date.getMonth() + 1,     //月份
        "d+": date.getDate(),     //日
        "h+": date.getHours(),     //小时
        "m+": date.getMinutes(),     //分
        "s+": date.getSeconds(),     //秒
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds()    //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

//将一条数据从一个dg添加到另一个dg
function oldDgToNewDg(oldDg, newDg) {
    var rows = $(oldDg).datagrid("getSelections");
    console.log(rows);
    console.log(rows.length);
    if (rows.length < 1) {
        alert("请选择要添加的信息！");
        return;
    }
    for (var i = 0; i < rows.length; i++) {
        $(newDg).datagrid("appendRow", rows[i]);
    }
}

//返回一个选中的行的id
function getDgId(dg) {
    var ids = [];
    var rows = $(dg).datagrid("getSelections");
    if (rows.length < 1) {
        alert("请选择要修改的数据");
        return;
    }
    for (var i = 0; i < rows.length; i++) {
        ids.push(rows[i].id);
    }
    var idarr = ids.join(",");
    return idarr;
}

