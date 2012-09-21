$(document).ready(function(){
	
	/*
	 * When the editor is opened
	 */
	$("#showEditorLink").click(function(){
		
		$('#topNavigation').toggle();
		$('#footer').toggle();	
		$('#detailsDiv').toggle();
		$('#contentDiv').toggle();
		$("#showEditorLink").toggle();
		$("#closeEditorContainer").toggle();
		
		$("#siteSaveButton").removeClass("saveButtonOnDetailsPage");
		$("#siteSaveButton").addClass("saveButtonOnEditorPage");
		
		$('#siteFormMain').removeClass("genericForm");
		
		CKEDITOR.replace( 'content', {
		});
		
	});
	
	/*
	 * When the editor is closed
	 */
	$("#closeEditorContainer").click(function(){
		
		$('#topNavigation').toggle();
		$('#footer').toggle();
		$('#detailsDiv').toggle();
		$('#contentDiv').toggle();
		$("#showEditorLink").toggle();
		$("#closeEditorContainer").toggle();
		
		$("#siteSaveButton").removeClass("saveButtonOnEditorPage");
		$("#siteSaveButton").addClass("saveButtonOnDetailsPage");
		
		$('#siteFormMain').addClass("genericForm");
		
	});
	
	/*
	 * Site form submission
	 */
	$('#siteSaveButton').click(function () {
		$("#siteForm").submit();  
		return false;
	});
	
 });
