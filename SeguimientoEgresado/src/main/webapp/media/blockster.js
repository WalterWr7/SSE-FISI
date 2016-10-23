/* ------------------
| NAMESPACE
------------------ */

Blockster = function(params, indexOfSlideToTurnOn) { 
	
	
	/* ------------------
	| PREP & CONFIG DEFAULTS
	------------------ */
	
	var thiss = this;
	this.conf = {}; //config
	this.conf.blocks = {rows: 10, cols: 10} //how many blocks do you want involved in the transition? (default 3 * 4, = 12)
	this.conf.pause = 4000; //miliseconds between slide transitions
	this.conf.animType = 'fade'; //how the blocks appear - 'simple' or 'fade'
	this.conf.blockAnimSpeed = 50; //miliseconds between each block transition
	this.params = params;
	this.clickActionQueueInt = false;
	
	
	/* ------------------
	| validate params. If anything not set, use defaults. If no or invalid holder, die.
	------------------ */
	
	if (!this.params.holder || $(this.params.holder).length != 1) {
		console.log("Blockster error: could not find holder element '"+this.params.holder+"'");
		return false;
	}
	this.params.holder = $(this.params.holder);
	if (!this.params.pause || !parseInt(this.params.pause)) this.params.pause = this.conf.pause;
	if (!this.params.blockAnimSpeed || !parseInt(this.params.blockAnimSpeed)) this.params.blockAnimSpeed = this.conf.blockAnimSpeed;
	if (!this.params.rows || !parseInt(this.params.rows)) this.params.rows = this.conf.blocks.rows;
	if (!this.params.cols || !parseInt(this.params.cols)) this.params.cols = this.conf.blocks.cols;
	
	
	/* ------------------
	| PREP
	------------------ */
	
	this.params.holder.dimensions = {width: this.params.holder.width(), height: this.params.holder.height()};
	this.params.dimensions = {
		width: Math.ceil(this.params.holder.dimensions.width / this.params.cols),
		height: Math.ceil(this.params.holder.dimensions.height / this.params.rows)
	};
	if (!indexOfSlideToTurnOn) this.params.holder.children('div').not(':first-child').hide(); //to start with, hide all slides but first
	

	/* ------------------
	| if params OK, do effect
	------------------ */		

	if (this.params.startWithoutDelay) thiss.func(indexOfSlideToTurnOn);
	if (!this.params.runOnceOnly) {
		var delay = this.params.pause + ((this.params.rows * this.params.cols) * this.params.blockAnimSpeed);
		if (!indexOfSlideToTurnOn)
			thiss.autoInt = setInterval(function() { thiss.func(); }, delay);
	}
	
		
};


/* ------------------
| MAIN FUNC
------------------ */

Blockster.prototype.func = function(indexOfSlideToTurnOn) {


	/* ------------------
	| prep
	------------------ */
	var thiss = this;


	/* ------------------
	| ascertain current and next slides (find :visible <div>, then next is either is prev sibling or, if none, parent's last child)
	------------------ */
	
	var currentSlide = this.params.holder.children('div:visible');
	var nextSlide = !indexOfSlideToTurnOn ? currentSlide.nextAll().length != 0 ? currentSlide.next() : this.params.holder.children('div').first() : this.params.holder.children('div').eq(indexOfSlideToTurnOn);

	
	/* ------------------
	| Iterate over number of required blocks to be built. For each...
	------------------ */
	
	for(var u=0; u<(this.params.rows * this.params.cols); u++) {
		
		
		/* ------------------
		| ...DOM-script the block and style/position it (position depends on number of rows/cols and block dimensions, worked out at top)
		| Remember to start each block as hidden, so we can bring them in randomly when all are ready.
		------------------ */
		
		var block = document.createElement('div');
		var left, top;
		$(block)
			.css({
				width: this.params.dimensions.width,
				height: this.params.dimensions.height,
				left: (left = (u % this.params.cols) * this.params.dimensions.width),
				top: (top = Math.floor(u / this.params.cols) * this.params.dimensions.height),
				zIndex: 10000,
				overflow: 'hidden',
				position: 'absolute'
			})
			.hide()
			.addClass('block');


		/* ------------------
		| ...clone next slide and append it to the block, positioning it so only the right part of it shows (hence overflow:hidden on blocks)
		| Remember to unhide it!
		------------------ */
		
		var nextSlide_clone = nextSlide.get(0).cloneNode(true);
		$(nextSlide_clone)
			.show()
			.css({
				height: this.params.holder.height(),
				width: this.params.holder.width(),
				position: 'relative',
				left: -left,
				top: -top
			});
		
		$(block).append(nextSlide_clone);
			
		
		/* ------------------
		| ...append it to holder
		------------------ */
		
		this.params.holder.append($(block));
		
		
	}
	
	
	/* ------------------
	| ANIMATION - with all blocks built, set an interval to turn them all on, one by one.
	| When all blocks in position, and all have finished anim (if fade rather than simple)
	|	- kill int
	|	- shuffle slides so the one our blocks contain parts of is genuinely topmost
	|	- remove blocks
	------------------ */
	
	var animInt = setInterval(function() {
		if(thiss.params.holder.children('.block:not(:visible)').length > 0) {
			var blocks = thiss.params.holder.children('.block:not(:visible)');
			with($(blocks.get(!thiss.params.random ? 0 : Math.floor(Math.random() * blocks.length)))) thiss.params.animType == 'simple' ? show() : fadeIn();
		}
		else if ($('.block:animated').length == 0) {
			clearInterval(animInt);
			nextSlide.siblings().hide();
			nextSlide.show();
			$('.block').remove();
		}
	}, thiss.params.blockAnimSpeed);
	
}


/* ------------------
| JUMP - allow user to control Blockster by interrupting auto-int and forcing a particular slide to load. Kills auto-int. If transition currently
| in progress, wait until after finished before acting. If another click logged while waiting, forget previous click (cancel its wait queue)
------------------ */

Blockster.prototype.jump = function(slideIndex) {
	if (this.clickActionQueueInt) { clearInterval(thiss.clickActionQueueInt); thiss.clickActionQueueInt = false; }
	var thiss = this;
	this.clickActionQueueInt = setInterval(function() {
		if (thiss.params.holder.children('.block').length == 0) {
			clearInterval(this.autoInt);
			thiss.func(slideIndex);
			clearInterval(thiss.clickActionQueueInt);
			thiss.clickActionQueueInt = false;
		}
	}, 10);
}