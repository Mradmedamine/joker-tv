$(function() {

    var channelContainer = $('#channels-container');
    
    initChannelsDataTables();

    function initChannelsDataTables() {
	var channelsDataTable = $('#channels-datatables').DataTable(dataTablesConfig);
    }

});