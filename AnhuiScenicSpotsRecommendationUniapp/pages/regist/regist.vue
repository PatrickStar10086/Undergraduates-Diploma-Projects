<template>

	<view>

		<form @submit="registactjson">
			<view class="form biaodan">
				<input maxlength="-1" type="text" class="input" name="username" placeholder="请输入账号" />
				<input maxlength="-1" type="text" class="input" name="password" placeholder="请输入密码" />
				<input maxlength="-1" type="password" class="input" name="repassword" placeholder="请确认密码" />
				<input maxlength="-1" name="identity" v-model="identityarray[identityindex]" style="display: none;" />
				<picker class="input" :range="identityarray" @change="bindidentityChange">
					<view>{{identityarray[identityindex]}}</view>
				</picker>
			</view>

			<button form-type="submit" class="btn biaodanbtn">注册</button>
		</form>


		<view class="tishi">
			<view class="zhuce">
				<view style="text-align: center;">
					已账号，点击<span @click="urlto('../login/login')">登录</span>
				</view>
			</view>
		</view>

		<view class="kefu">
			景点推荐 客服电话：400-400-400
		</view>

	</view>

</template>

<script>
	export default {
		data() {
			return {
				identityarray: ["用户", ],
				identityindex: 0,
			}
		},
		methods: {
			urlto(url) {
				uni.navigateTo({
					url: url
				})
			},
			bindidentityChange(e) {
				this.identityindex = e.detail.value;
				this.identitystr = this.identityarray[this.identityindex];
			},
			registactjson(e) {
				let _this = this
				uni.request({
					url: "http://localhost:8080/ahjdtj/registactjson.action",
					method: "POST",
					data: JSON.stringify(e.detail.value),
					success(res) {
						console.log(res)
						let message = res.data.message;

						console.log(message);

						if (message == '注册成功，请登录') {
							uni.showToast({
								icon: "none",
								title: "注册成功，请登录"
							})
							_this.urlto('../login/login')
						}

						if (message == '两次密码不一致') {
							uni.showToast({
								icon: "none",
								title: "注册失败，两次密码不一致"
							})
						}

						if (message == '该账号已存在') {
							uni.showToast({
								icon: "none",
								title: "注册失败，该账号已存在"
							})
						}

						if (message == '请选择登录身份') {
							uni.showToast({
								icon: "none",
								title: "请选择登录身份"
							})
						}

					}
				})
			},
		},
		onLoad(e) {

		},
	}
</script>

<style>
	page {
		background-color: #f8f8f8;
	}

	.input {
		border-bottom: 1px solid #d6d6d6;
		padding: 10px;
		margin: 5px 10px;
	}

	.form {
		background-color: #FFFFFF;
		margin-top: 15px;
		padding: 0 0px 15px 0px;
	}

	.biaodanbtn {
		color: #FFFFFF;
		background-color: #df2d2d;
		margin: 15px;
	}

	.tishi {
		padding: 0 10px;
		color: #FE8C00;
		font-size: 14px;
	}

	.kefu {
		color: #df2d2d;
		text-align: center;
		font-size: 14px;
		margin-top: 10px;
	}
</style>
