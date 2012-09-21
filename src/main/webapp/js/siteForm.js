$(document).ready(function(){
	
	$("#showEditorLink").click(function(){
		$('#topNavigation').toggle();
		$('#footer').toggle();	
		$('#detailsDiv').toggle();
		$('#contentDiv').toggle();
		//$("#showEditorContainer").toggle();
		$("#showEditorLink").toggle();
		$("#closeEditorContainer").toggle();
		$('#siteFormMain').removeClass("genericForm");
		CKEDITOR.replace( 'content', {

		});
		//$('#cke_contents_content').addClass("ckEditorHeight");
	});
	
	$("#closeEditorContainer").click(function(){
		$('#topNavigation').toggle();
		$('#footer').toggle();
		$('#detailsDiv').toggle();
		$('#contentDiv').toggle();
		//$("#showEditorContainer").toggle();
		$("#showEditorLink").toggle();
		$("#closeEditorContainer").toggle();
		$('#siteFormMain').addClass("genericForm");
	});
	
 });

function addContent(){
	//$("#hiddenContent").val("pra<b>te</b>");
}