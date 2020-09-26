<template>
    <div>
        <AppBar :isLoaded="true" @onopendrawer="$emit('onopendrawer')"/>
    </div>
</template>
<script>
import AppBar from '../GlobalComponents/AppBar'
export default {
    components:{
        AppBar
    }
}
</script>
<style scoped>

</style>