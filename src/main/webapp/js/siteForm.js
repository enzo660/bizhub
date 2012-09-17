$(document).ready(function(){
	
	$("#showEditorLink").click(function(){
		$('#detailsDiv').toggle();
		$('#contentDiv').toggle();
		$("#showEditorLink").toggle();
		$("#closeEditorLink").toggle();
		//$('#siteFormMain').removeClass("genericForm");
		CKEDITOR.replace( 'content' );
	});
	
	$("#closeEditorLink").click(function(){
		$('#detailsDiv').toggle();
		$('#contentDiv').toggle();
		$("#showEditorLink").toggle();
		$("#closeEditorLink").toggle();
	});
	
 });

function addContent(){
	//$("#hiddenContent").val("pra<b>te</b>");
}