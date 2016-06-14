jQuery.listTolist = function(fromid,toid,moveOrAppend,isAll) {
	if(moveOrAppend.toLowerCase() == "move") {
		if(isAll == true) {
			$("#"+fromid+" option").each(function() {
				$(this).appendTo($("#"+toid+":not(:has(option[value="+$(this).val()+"]))"));
			});
			$("#"+fromid).empty();
		}
		else if(isAll == false) {
			$("#"+fromid+" option:selected").each(function() {
				$(this).appendTo($("#"+toid+":not(:has(option[value="+$(this).val()+"]))"));
				if($("#"+fromid+" option[value="+$(this).val()+"]").length > 0) {
					$("#"+fromid).get(0)
					.removeChild($("#"+fromid+" option[value="+$(this).val()+"]").get(0));
				}
			});
		}
	}
	else if(moveOrAppend.toLowerCase() == "append") {
		if(isAll == true) {
			$("#"+fromid+" option").each(function() {
				$("<option></option>")
				.val($(this).val())
				.text($(this).text())
				.appendTo($("#"+toid+":not(:has(option[value="+$(this).val()+"]))"));
			});
		}
		else if(isAll == false) {
			$("#"+fromid+" option:selected").each(function() {
				$("<option></option>")
				.val($(this).val())
				.text($(this).text())
				.appendTo($("#"+toid+":not(:has(option[value="+$(this).val()+"]))"));
			});
		}
	}
};

jQuery.list2list = function(fromid,toid,isAll) {
	if(isAll == true) {
		$("#"+fromid+" option").each(function() {
			$(this).appendTo($("#"+toid+":not(:has(option[value="+$(this).val()+"]))"));
		});
	}
	else if(isAll == false) {
		$("#"+fromid+" option:selected").each(function() {
			$(this).appendTo($("#"+toid+":not(:has(option[value="+$(this).val()+"]))"));
		});
	}
};