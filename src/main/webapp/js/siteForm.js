$(document).ready(function(){
	
	$("#showEditorLink").click(function(){
		$('#topNavigation').toggle();
		$('#footer').toggle();	
		$('#detailsDiv').toggle();
		$('#contentDiv').toggle();
		$("#showEditorLink").toggle();
		$("#closeEditorLink").toggle();
		$('#siteFormMain').removeClass("genericForm");
		CKEDITOR.replace( 'content', {

		});
		//$('#cke_contents_content').addClass("ckEditorHeight");
	});
	
	$("#closeEditorLink").click(function(){
		$('#topNavigation').toggle();
		$('#footer').toggle();
		$('#detailsDiv').toggle();
		$('#contentDiv').toggle();
		$("#showEditorLink").toggle();
		$("#closeEditorLink").toggle();
		$('#siteFormMain').addClass("genericForm");
	});
	
 });

function addContent(){
	//$("#hiddenContent").val("pra<b>te</b>");
}