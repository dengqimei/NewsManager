String.prototype.trim=function(){
	var m=this.replace(/(^[\s]*)|([\s]*$)/g,"");
	return m;
}