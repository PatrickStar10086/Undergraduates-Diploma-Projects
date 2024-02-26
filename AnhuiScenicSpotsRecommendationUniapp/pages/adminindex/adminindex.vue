<template>

	<view>
		<form @submit="updateadminactjson">
			<view class="form biaodan">
				<input maxlength="-1" type="text" v-model="admin.id" style="display: none;" name="id" /><input
					maxlength="-1" name="username" v-model="admin.username" disabled class="input"
					placeholder="请输入账号" /><input maxlength="-1" name="password" v-model="admin.password" class="input"
					placeholder="请输入密码" />
			</view>


			<button class="btn biaodanbtn" form-type="submit">修改</button>
		</form>
		<view class="kefu">
			景点推荐 客服电话：400-400-400
		</view>

	</view>

</template>

<script>
	export default {
		data() {
			return {
				admin: [],
				id: '',

			}
		},
		methods: {
			updateadminactjson(e) {
				let _this = this
				uni.request({
					url: "http://localhost:8080/ahjdtj/updateadminactjson.action",
					method: "POST",
					data: JSON.stringify(e.detail.value),
					success(res) {
						console.log(res)

						uni.showToast({
							icon: "none",
							title: "修改管理员个人信息成功"
						})
					}
				})
			},


		},
		onLoad(e) {

			let _this = this;
			uni.getStorage({
				key: 'id',
				success(res) {
					console.log(res);
					_this.id = res.data;

					uni.request({
						url: "http://localhost:8080/ahjdtj/admindetailsjson.action?id=" + _this.id,
						success: res => {
							console.log(res.data)
							_this.admin = res.data.admin;
						}
					})

				}
			});
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

	.kefu {
		color: #df2d2d;
		text-align: center;
		font-size: 14px;
		margin-top: 10px;
	}
</style>
