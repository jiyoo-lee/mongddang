import { useState } from "react";
import { useParams } from "react-router-dom";
import PutAxios from "../../utils/axios/PutAxios";
import MyButton from "../button/MyButton";

const GuestBookEditor = ({guestBookId}) => {
    const { userId } = useParams();
    const [edit, setEdit] = useState(false);
    const [guestBook, setGuestBook] = useState("");

    const updateRequestBody = {
        guestId: sessionStorage.getItem("userId"),
        guestBookId: guestBookId,
        contents: guestBook
    }
    const editForm = <>
                        <input type="text" value={guestBook} placeholder="수정내용을 입력하세요." onChange={(e)=>(setGuestBook(e.target.value))}/>
                        <button className ="common_btn" onClick={()=>{
                            PutAxios('/drawer/' + userId + '/guestbook', updateRequestBody, (res)=>{
                                window.location.reload();
                            });
                        }}>수정</button>
                        <button className ="common_btn" onClick={(e)=>(setEdit(false))}>수정취소</button>
                    </>

    const editWord = <button className ="common_btn" onClick={(e)=>(setEdit(true))}> 수정 </button>

    return (
        <>
            {edit ? editForm : editWord}
        </>
    );
}

export default GuestBookEditor;