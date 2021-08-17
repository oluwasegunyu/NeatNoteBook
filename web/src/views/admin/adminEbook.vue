<template>
  <a-layout>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <a-table
          :columns="columns"
          :data-source="ebooks"
          :row-key="record => record.id"
          :pagination="pagination"
          :loading="loading"
          @change="handleTableChange"
      >
        <template #cover="{ text:cover }">
          <img v-if="cover" :src="cover" alt="avatar" />
        </template>
        <template v-slot:action="{}">
          <a-space size="small">
            <a-button type="primary" @click="edit">
              编辑
            </a-button>
            <a-button type="danger">
              删除
            </a-button>
          </a-space>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>

  <a-modal v-model:visible="modalVisible" title="笔记表单" @ok="handleModalOk">
    <p>Some contents...</p>
  </a-modal>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref} from 'vue';
import axios from "axios";

export default defineComponent({
  name:'AdminEbook',
  setup() {
    const ebooks = ref();
    const pagination = ref({
      current: 1,
      pageSize: 4,
      total:0
    });
    const loading = ref(false);

    const columns = [
      {
        title: '封面',
        key: 'cover',
        dataIndex: 'cover',
        slots: { customRender: 'cover' },
      },
      {
        title: '名称',
        key: 'name',
        dataIndex: 'name',
      },
      {
        title: '类别',
        key: 'category1Id',
        dataIndex: 'category1Id',
      },
      {
        title: '子类',
        dataIndex: 'category2Id',
      },
      {
        title: '阅读',
        dataIndex: 'viewCount',
      },
      {
        title: '点赞',
        dataIndex: 'voteCount',
      },
      {
        title: 'Action',
        key: 'action',
        slots: { customRender: 'action' },
      },
    ];

    /**
     * 数据查询
     */
    const handleQuery = (params: any) => {
      loading.value = true;
      axios.get("/ebook/list", {
        params: {
          page: params.page,
          size: params.size,
        }
      }).then((response)=>{
        loading.value = false;
        const data = response.data;
        ebooks.value = data.content.list;

        pagination.value.current = params.page;
        pagination.value.total = data.content.total;
      });
    };

    /**
     * 点击页码触发
     */
    const handleTableChange = (pagination: any) => {
      handleQuery({
        page: pagination.current,
        size: pagination.pageSize
      });
    };

    //----表单-----
    const modalVisible = ref<boolean>(false);
    const showModal = () => {
      modalVisible.value = true;
    };
    const handleModalOk = (e: MouseEvent) => {
      console.log(e);
      modalVisible.value = false;
    };

    /**
     * 编辑
     */
    const edit = () => {
      modalVisible.value = true;
    }



    onMounted(()=>{
      handleQuery({
        page: 1,
        size: pagination.value.pageSize
      });
    });

    return {
      ebooks,
      pagination,
      loading,
      handleTableChange,
      columns,

      //编辑
      edit,
      modalVisible,
      showModal,
      handleModalOk,
    }
  }
});
</script>

<style scoped>
img {
  width: 50px;
  height: 50px;
}
</style>