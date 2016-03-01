$(document).ready(function() {
		$('#date_birth').datepicker({
			format: 'yyyy-mm-dd',
			calendarWeeks : true,
			todayHighlight : true,
			toggleActive : true,
			todayHighlight : true,
			clearBtn : true,
			autoclose : true
		});
		$('#date_death').datepicker({
			format: 'yyyy-mm-dd',
			calendarWeeks : true,
			todayHighlight : true,
			toggleActive : true,
			todayHighlight : true,
			clearBtn : true,
			autoclose : true
		});
	});
