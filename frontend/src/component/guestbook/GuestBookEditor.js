import { useState } from "react";
import { useParams } from "react-router-dom";
import PutAxios from "../../utils/axios/PutAxios";

const GuestBookEditor = ({guestBookId}) => {
    const { userId } = useParams();
    const [edit, setEdit] = useState(false);
    const [guestBook, setGuestBook] = useState("");

    const updateRequestBody = {
        guestId: sessionStorage.getItem("userId"),
        guestBookId: guestBookId,
        contents: guestBook
    }
    const editForm = <div>
                        <input type="text" value={guestBook} placeholder="수정내용을 입력하세요." onChange={(e)=>(setGuestBook(e.target.value))}/>
                        <button onClick={()=>{
                            PutAxios('/drawer/' + userId + '/guestbook', updateRequestBody, (res)=>{
                                window.location.reload();
                            });
                        }}>수정</button>
                        <button onClick={(e)=>(setEdit(false))}>수정취소</button>
                    </div>

    const editWord = <div onClick={(e)=>(setEdit(true))}>수정</div>

    return (
        <div>
            {edit ? editForm : editWord}
        </div>
    );
}

export default GuestBookEditor;