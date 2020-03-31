/* 
* Freakworm Manager basic javascript code
* Copyright (c) 2012 by Titus Kruse
*/
	
function addOnclickToDatatableRows(dataTableId) {
	var trs = document.getElementById(dataTableId).getElementsByTagName('tbody')[0].getElementsByTagName('tr');
	for ( var i = 0; i < trs.length; i++) {
		trs[i].onclick = new Function("highlightAndSelectRow('" + dataTableId + "', this)");
	}
}

function highlightAndSelectRow(dataTableId, tr) {
	var trs = document.getElementById(dataTableId).getElementsByTagName('tbody')[0].getElementsByTagName('tr');
	for ( var i = 0; i < trs.length; i++) {
		if (trs[i] == tr) {
			var checkbox = trs[i].getElementsByTagName('input')[0];
			if (checkbox) {
				checkbox.checked = !checkbox.checked;
			}
		}
	}
}

function confirmDelete() {
	return confirm('Sollen die ausgewaehlten Eintraege wirklich geloescht werden?');
}
