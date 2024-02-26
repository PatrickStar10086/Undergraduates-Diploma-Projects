<template>

	<view>
		<form @submit="updateuseractjson">
			<view class="form biaodan">
				<input maxlength="-1" type="text" v-model="user.id" style="display: none;" name="id" /><input
					maxlength="-1" name="name" v-model="user.name" class="input" placeholder="请输入名字" /><input
					maxlength="-1" name="username" v-model="user.username" disabled class="input"
					placeholder="请输入账号" /><input maxlength="-1" name="password" v-model="user.password" class="input"
					placeholder="请输入密码" />
				<input maxlength="-1" name="sex" v-model="sexstr" style="display: none;" />
				<picker @change="bindsexChange" :range="sexarray">
					<view class="input">
						{{sexarray[sexindex]}}
					</view>
				</picker><input maxlength="-1" name="age" v-model="user.age" class="input"
					placeholder="请输入年龄" /><textarea name="introduce" v-model="user.introduce" auto-height class="input"
					style="width: auto;min-height: 100px;" placeholder="请输入介绍"></textarea>
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
				sexarray: ["男", "女"],
				sexindex: 0,
				sexstr: "",
				user: [],

			}
		},
		methods: {
			bindsexChange: function(e) {
				this.sexindex = e.detail.value;
				this.sexstr = this.sexarray[this.sexindex];
			},
			updateuseractjson(e) {
				let _this = this
				uni.request({
					url: "http://localhost:8080/ahjdtj/updateuseractjson.action",
					method: "POST",
					data: JSON.stringify(e.detail.value),
					success(res) {
						console.log(res)

						uni.showToast({
							icon: "none",
							title: "修改用户成功"
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
						url: "http://localhost:8080/ahjdtj/userdetailsjson.action?id=" + _this.id,
						success: res => {
							console.log(res.data)
							_this.user = res.data.user;
							_this.sexstr = _this.user.sex;
					
							for (let i = _this.sexarray.length - 1; i >= 0; i--) {
								if (_this.sexarray[i] == _this.sexstr) {
									_this.sexindex = i;
								}
					
							}
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
