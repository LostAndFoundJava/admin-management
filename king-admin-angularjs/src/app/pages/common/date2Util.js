function formatDate(inputTime) {
    var date = new Date(inputTime);
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    month = month < 10 ? ('0' + month) : month;
    var day = date.getDate();
    day = day < 10 ? ('0' + day) : day;
    var hour = date.getHours();
    hour = hour < 10 ? ('0' + hour) : hour;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    return year + '-' + month + '-' + day + ' ' + hour + ':' + minute;
}